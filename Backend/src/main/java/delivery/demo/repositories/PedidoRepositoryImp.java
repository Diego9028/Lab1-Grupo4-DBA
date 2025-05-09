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

    //4 Calcular el tiempo promedio entre pedido y entrega por repartidor.
    public List<Map<String, Object>> obtenerTiemposPromedioEntrega() {
        String sql = """
        SELECT 
          r.id_repartidor,
          r.nombre,
          AVG(EXTRACT(EPOCH FROM (d.hora_entrega - p.hora_pedido))) AS tiempo_promedio_entrega
        FROM 
          PEDIDO p
        JOIN 
          DETALLE_PEDIDO d ON p.id_detalle_pedido = d.id_detalle_pedido
        JOIN 
          REPARTIDOR r ON p.id_repartidor = r.id_repartidor
        WHERE 
          d.entregado = TRUE
        GROUP BY 
          r.id_repartidor, r.nombre
        ORDER BY 
          tiempo_promedio_entrega
    """;

        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetchTable()
                    .asList();
        }
    }

}
