package delivery.demo.repositories;

import delivery.demo.entities.UrgenciaEntity;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UrgenciaRepositoryImp {

    private final Sql2o sql2o;

    public UrgenciaRepositoryImp(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    //6 ¿Qué medio de pago se utiliza más en pedidos urgentes?
    public List<Map<String, Object>> obtenerMedioPagoMasUsadoEnUrgenciasAltas() {
        String sql = """
            SELECT\s
              mp.id_medio_pago,
              mp.tipo,
              COUNT(*) AS cantidad_usos
            FROM\s
              PEDIDO p
            JOIN\s
              URGENCIA u ON p.id_urgencia = u.id_urgencia
            JOIN\s
              MEDIO_PAGO mp ON p.id_medio_pago = mp.id_medio_pago
            WHERE\s
              u.tipo = 'Alta'
            AND p.deleted_at IS NULL
            AND u.deleted_at IS NULL
            AND mp.deleted_at IS NULL
            GROUP BY\s
              mp.id_medio_pago, mp.tipo
            ORDER BY\s
              cantidad_usos DESC
            LIMIT 1;
        """;
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetchTable()
                    .asList();
        }
    }

    // Crear una nueva urgencia
    public UrgenciaEntity create(UrgenciaEntity urgencia) {
        String sql = """
        INSERT INTO URGENCIA (tipo)
        VALUES (:tipo)
        RETURNING id_urgencia;
    """;
        try (org.sql2o.Connection con = sql2o.open()) {
            Long id = con.createQuery(sql)
                    .addParameter("tipo", urgencia.getTipo())
                    .executeUpdate()
                    .getKey(Long.class);
            urgencia.setId_urgencia(id);
            return urgencia;
        }
    }

    // Actualizar una urgencia existente
    public void update(UrgenciaEntity urgencia) {
        String sql = """
            UPDATE URGENCIA
            SET tipo = :tipo
            WHERE id_urgencia = :id_urgencia
            AND deleted_at IS NULL;
        """;
        try (org.sql2o.Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("tipo", urgencia.getTipo())
                    .addParameter("id_urgencia", urgencia.getId_urgencia())
                    .executeUpdate();
        }
    }

    // Obtener todas las urgencias
    public List<UrgenciaEntity> getAll() {
        String sql = """
            SELECT * FROM URGENCIA
            WHERE deleted_at IS NULL;
        """;
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(UrgenciaEntity.class);
        }
    }

    // Eliminar una urgencia
    public void delete(Long idUrgencia) {
        String sql = """
            UPDATE URGENCIA
            SET deleted_at = NOW()
            WHERE id_urgencia = :id_urgencia
              AND deleted_at IS NULL;
        """;
        try (org.sql2o.Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id_urgencia", idUrgencia)
                    .executeUpdate();
        }
    }
}
