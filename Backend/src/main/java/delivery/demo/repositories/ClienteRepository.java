package delivery.demo.repositories;

import delivery.demo.entities.ClienteEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository

public class ClienteRepository {

    @Query(value = """
        SELECT 
            c.id_cliente AS idCliente,
            c.direccion AS direccion,
            c.correo AS correo,
            SUM(ps.precio * pp.cantidad) AS totalGastado
        FROM CLIENTE c
        JOIN PEDIDO p ON c.id_cliente = p.id_cliente
        JOIN DETALLE_PEDIDO dp ON p.id_detalle_pedido = dp.id_detalle_pedido
        JOIN PEDIDO_PRODUCTO pp ON p.id_pedido = pp.id_pedido
        JOIN PRODUCTO_SERVICIO ps ON pp.id_producto_servicio = ps.id_producto_servicio
        WHERE dp.entregado = TRUE
        GROUP BY c.id_cliente, c.direccion, c.correo
        ORDER BY totalGastado DESC
        LIMIT 1
        """, nativeQuery = true)
    public ClienteEntity findClienteQueMasGasto() {
        return null;
    }
}
