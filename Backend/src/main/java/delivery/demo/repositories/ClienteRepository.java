package delivery.demo.repositories;

import delivery.demo.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

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
    ClienteEntity findClienteQueMasGasto();

    @Query(value = "SELECT c FROM ClienteEntity c JOIN PedidoEntity p ON c.id = p.idCliente JOIN DetallePedidoEntity dp ON p.idDetallePedido = dp.id JOIN PedidoProductoEntity pp ON p.id = pp.id JOIN ProductoServivioEntity ps ON pp.idProductoServicio = ps.id WHERE dp.entregado = TRUE GROUP BY c.id, c.direccion, c.correo ORDER BY SUM(ps.precio * pp.cantidad) DESC")
    ClienteEntity findClienteQueMasGasto_2();

    @Query("SELECT c FROM ClienteEntity c WHERE c.correo =:correo")
    Optional<ClienteEntity> findByCorreo(@Param("correo") String correo);

    @Query("SELECT c FROM ClienteEntity c")
    List<ClienteEntity> findAllClientes();

}
