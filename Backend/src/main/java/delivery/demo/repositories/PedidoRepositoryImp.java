package delivery.demo.repositories;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;
import java.util.Map;

@Repository
public class PedidoRepositoryImp {

    private final Sql2o sql2o;

    public PedidoRepositoryImp(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<Map<String, Object>> obtenerMasPedidosPorCategoriaUltimoMes() {
        String sql = """
        SELECT 
          c.nombre AS categoria,
          ps.id_producto_servicio,
          SUM(pp.cantidad) AS total_pedidos
        FROM PEDIDO_PRODUCTO pp
        JOIN PRODUCTO_SERVICIO ps ON pp.id_producto_servicio = ps.id_producto_servicio
        JOIN CATEGORIA c ON ps.id_categoria = c.id_categoria
        JOIN PEDIDO p ON pp.id_pedido = p.id_pedido
        WHERE p.hora_pedido >= NOW() - INTERVAL '1 month'
        GROUP BY c.nombre, ps.id_producto_servicio
        ORDER BY c.nombre, total_pedidos DESC
    """;

        try (Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .executeAndFetchTable()
                    .asList(); // Devuelve una lista de filas como Map<String, Object>
        }
    }
}
