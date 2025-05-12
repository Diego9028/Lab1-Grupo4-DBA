package delivery.demo.repositories;

import delivery.demo.entities.CalificacionEntity;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CalificacionRepository {

    private final Sql2o sql2o;

    public CalificacionRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    public CalificacionEntity crear(CalificacionEntity calificacion) {
        String sql = """
            INSERT INTO CALIFICACION (total_puntos, total_pedidos, promedio, id_repartidor)
            VALUES (:tp, :td, :pm, :idr)
            RETURNING id_calificacion
        """;

        try (Connection con = sql2o.open()) {
            Integer id = con.createQuery(sql)
                    .addParameter("tp", calificacion.getTotal_puntos())
                    .addParameter("td", calificacion.getTotal_pedidos())
                    .addParameter("pm", calificacion.getPromedio())
                    .addParameter("idr", calificacion.getId_repartidor())
                    .executeUpdate()
                    .getKey(Integer.class);
            calificacion.setId_calificacion(id);
            return calificacion;
        }
    }

    public List<CalificacionEntity> getAll() {
        String sql = """
        SELECT * FROM CALIFICACION
        WHERE deleted_at IS NULL
        """;
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                            .executeAndFetch(CalificacionEntity.class);
        }
    }


    public boolean update(CalificacionEntity calificacion, Integer id) {
        String sql = """
            UPDATE CALIFICACION
            SET total_puntos = :tp,
                total_pedidos = :td,
                promedio = :pm
            WHERE id_calificacion = :id AND deleted_at IS NULL
        """;

        try (Connection con = sql2o.open()) {
            int updatedRows = con.createQuery(sql)
                    .addParameter("tp", calificacion.getTotal_puntos())
                    .addParameter("td", calificacion.getTotal_pedidos())
                    .addParameter("pm", calificacion.getPromedio())
                    .addParameter("id", id)
                    .executeUpdate()
                    .getResult();
            return updatedRows > 0;
        }
    }

    public void delete(Integer id) {
        String sql = """
        UPDATE CALIFICACION
        SET deleted_at = NOW()
        WHERE id_calificacion = :id AND deleted_at IS NULL
    """;

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
