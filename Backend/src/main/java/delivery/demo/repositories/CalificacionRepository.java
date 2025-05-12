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
            RETURNING id_calificacion, total_puntos, total_pedidos, promedio, id_repartidor
        """;

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("tp", calificacion.getTotalPuntos())
                    .addParameter("td", calificacion.getTotalPedidos())
                    .addParameter("pm", calificacion.getPromedio())
                    .addParameter("idr", calificacion.getIdRepartidor())
                    .executeAndFetchFirst(CalificacionEntity.class);
        }
    }

    public List<CalificacionEntity> getAll() {
        String sql = """
        SELECT
            id_calificacion AS idCalificacion,
            total_puntos AS totalPuntos,
            total_pedidos AS totalPedidos,
            promedio,
            id_repartidor AS idRepartidor
        FROM CALIFICACION
    """;

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(CalificacionEntity.class);
        }
    }


    public boolean update(CalificacionEntity calificacion, Integer id) {
        String sql = """
            UPDATE CALIFICACION
            SET total_puntos = :tp,
                total_pedidos = :td,
                promedio = :pm
            WHERE id_calificacion = :id
        """;

        try (Connection con = sql2o.open()) {
            int updatedRows = con.createQuery(sql)
                    .addParameter("tp", calificacion.getTotalPuntos())
                    .addParameter("td", calificacion.getTotalPedidos())
                    .addParameter("pm", calificacion.getPromedio())
                    .addParameter("id", id)
                    .executeUpdate()
                    .getResult();
            return updatedRows > 0;
        }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM CALIFICACION WHERE id_calificacion = :id";

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
