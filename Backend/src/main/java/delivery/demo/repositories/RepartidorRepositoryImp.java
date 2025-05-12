package delivery.demo.repositories;

import delivery.demo.entities.RepartidorEntity;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RepartidorRepositoryImp {

    private final Sql2o sql2o;

    public RepartidorRepositoryImp(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    //5 Obtener los 3 repartidores con mejor rendimiento (por entregas y promedio de calificación).
    public List<Map<String, Object>> obtenerTop3Repartidores() {
        String sql = """
            SELECT
              r.id_repartidor,
              r.nombre,
              COALESCE(e.entregas, 0) AS total_entregas,
              COALESCE(c.promedio, 0) AS promedio_puntuacion
            FROM REPARTIDOR r
            LEFT JOIN (
              SELECT
                p.id_repartidor,
                COUNT(*) AS entregas
              FROM PEDIDO p
              INNER JOIN DETALLE_PEDIDO d ON p.id_detalle_pedido = d.id_detalle_pedido
              WHERE d.entregado = TRUE
              GROUP BY p.id_repartidor
            ) e ON r.id_repartidor = e.id_repartidor
            LEFT JOIN CALIFICACION c ON r.id_repartidor = c.id_repartidor
            ORDER BY
              total_entregas DESC,
              promedio_puntuacion DESC
            LIMIT 3;
        """;

        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetchTable()
                    .asList();
        }
    }

    //Desempeño por repartidor
    public List<Map<String, Object>> obtenerDesempenoPorRepartidor() {
        String sql = """
        SELECT\s
          r.id_repartidor,
          r.nombre,
          c.total_puntos,
          c.total_pedidos,
          c.promedio
        FROM\s
          REPARTIDOR r
        JOIN\s
          CALIFICACION c ON r.id_repartidor = c.id_repartidor
        ORDER BY\s
          c.total_puntos DESC;
    """;

        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetchTable()
                    .asList();
        }
    }

    public List<RepartidorEntity> obtenerTodos() {
        String sql = "SELECT id_repartidor AS id, nombre, id_empresa_asociada AS idEmpresaAsociada FROM REPARTIDOR";

        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(RepartidorEntity.class);
        }
    }

    public void update(RepartidorEntity repartidor) {
        String sql = """
        UPDATE REPARTIDOR
        SET nombre = :nombre,
            id_empresa_asociada = :idEmpresaAsociada
        WHERE id_repartidor = :id
    """;

        try (org.sql2o.Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", repartidor.getId())
                    .addParameter("nombre", repartidor.getNombre())
                    .addParameter("idEmpresaAsociada", repartidor.getIdEmpresaAsociada())
                    .executeUpdate();
        }
    }

    public void crearRepartidor(RepartidorEntity repartidor) {
        String sql = "INSERT INTO REPARTIDOR (nombre, id_empresa_asociada) VALUES (:nombre, :idEmpresaAsociada)";
        try (org.sql2o.Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("nombre", repartidor.getNombre())
                    .addParameter("idEmpresaAsociada", repartidor.getIdEmpresaAsociada())
                    .executeUpdate();
        }
    }

}
