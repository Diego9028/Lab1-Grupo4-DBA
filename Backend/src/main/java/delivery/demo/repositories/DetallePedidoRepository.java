package delivery.demo.repositories;

import delivery.demo.entities.DetallePedidoEntity;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DetallePedidoRepository {

    @Autowired
    private Sql2o sql2o;

    //El detalle del pedido se crea al momento de crear un pedido

    public List<DetallePedidoEntity> getAll() {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM detalle_pedido";
            return con.createQuery(sql).executeAndFetch(DetallePedidoEntity.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean update(DetallePedidoEntity detalle) {
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE detalle_pedido SET " +
                    "entregado = :entregado, " +
                    "hora_entrega = :hora_entrega, " +
                    "calificacion = :calificacion " +
                    "WHERE id_detalle_pedido = :id_detalle_pedido";

            con.createQuery(sql)
                    .addParameter("entregado", detalle.isEntregado())
                    .addParameter("hora_entrega", detalle.getHora_entrega())
                    .addParameter("calificacion", detalle.getCalificacion())
                    .addParameter("id_detalle_pedido", detalle.getId_detalle_pedido())
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
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

    //El delete se lleva a cabo en el delete de pedido
}
