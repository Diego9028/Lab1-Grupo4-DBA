package delivery.demo.repositories;

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
}
