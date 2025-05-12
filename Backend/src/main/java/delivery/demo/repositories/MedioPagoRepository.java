package delivery.demo.repositories;

import delivery.demo.entities.MedioPagoEntity;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedioPagoRepository {

    private final Sql2o sql2o;

    public MedioPagoRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public MedioPagoEntity save(MedioPagoEntity medioPago) {
        String sql = """
            INSERT INTO medio_pago (tipo)
            VALUES (:tipo)
            RETURNING id_medio_pago
        """;

        try (Connection con = sql2o.open()) {
            Long id = con.createQuery(sql)
                    .addParameter("tipo", medioPago.getTipo())
                    .executeUpdate()
                    .getKey(Long.class);
            medioPago.setId_medio_pago(id);
            return medioPago;
        }
    }

    public List<MedioPagoEntity> findAll() {
        String sql = """
            SELECT * FROM medio_pago
            WHERE deleted_at IS NULL
        """;

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(MedioPagoEntity.class);
        }
    }

    public void delete(Long id) {
        String sql = """
            UPDATE medio_pago
            SET deleted_at = NOW()
            WHERE id_medio_pago = :id
              AND deleted_at IS NULL
        """;

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public void update(Long id, MedioPagoEntity medioPago) {
        String sql = """
            UPDATE medio_pago
            SET tipo = :tipo
            WHERE id_medio_pago = :id
              AND deleted_at IS NULL
        """;

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("tipo", medioPago.getTipo())
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}