package delivery.demo.repositories;

import delivery.demo.entities.PedidoProductoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Optional;

@Repository

public class PedidoProductoRepository {

    @Autowired
    private final Sql2o sql2o;

    public PedidoProductoRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<PedidoProductoEntity> obtenerPedidoProductoPorId(Long id) {
        String sql = """
                SELECT * FROM pedido_producto WHERE id_pedido = :id AND deleted_at IS NULL
                """;

        try (Connection con = sql2o.open()) {
            List<PedidoProductoEntity> pedidoProducto = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(PedidoProductoEntity.class);
            return Optional.ofNullable(pedidoProducto)
                    .orElseThrow(() -> new RuntimeException("No se encontró el detalle de pedido con id: " + id));
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
            return null;
        }
    }
}