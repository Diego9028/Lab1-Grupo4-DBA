package delivery.demo.repositories;

import delivery.demo.entities.DetallePedidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Optional;

@Repository

public class DetallePedidoRepository {

    @Autowired
    private final Sql2o sql2o;
    public DetallePedidoRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public DetallePedidoEntity obtenerDetallePedidoPorId(Long id) {

        String sql = """
            SELECT * FROM detalle_pedido WHERE id_detalle_pedido = :id
            """;

        try (Connection con = sql2o.open()) {
            DetallePedidoEntity detallePedido = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(DetallePedidoEntity.class);
            return Optional.ofNullable(detallePedido)
                    .orElseThrow(() -> new RuntimeException("No se encontró el detalle de pedido con id: " + id));
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
            return null;
        }
    }
}
