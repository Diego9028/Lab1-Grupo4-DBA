package delivery.demo.repositories;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.Optional;

@Repository
public class ClienteRepositoryImp {

    private final Sql2o sql2o;

    public ClienteRepositoryImp(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public Optional<Long> obtenerClienteConMasGasto() {
        String sql = """
        SELECT 
          c.id
        FROM CLIENTES c
        JOIN PEDIDO p ON c.id = p.id_cliente
        JOIN DETALLE_PEDIDO dp ON p.id_detalle_pedido = dp.id_detalle_pedido
        JOIN PEDIDO_PRODUCTO pp ON p.id_pedido = pp.id_pedido
        JOIN PRODUCTO_SERVICIO ps ON pp.id_producto_servicio = ps.id_producto_servicio
        WHERE dp.entregado = TRUE
        GROUP BY c.id
        ORDER BY SUM(ps.precio * pp.cantidad) DESC
        LIMIT 1
    """;

        try (Connection conn = sql2o.open()) {
            return Optional.ofNullable(conn.createQuery(sql)
                    .executeScalar(Long.class));
        }
    }
}
