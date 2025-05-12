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

    public List<DetallePedidoEntity> getAll() {
        String sql = """
                SELECT * FROM detalle_pedido
                WHERE deleted_at IS NULL
                """;
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(DetallePedidoEntity.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean update(DetallePedidoEntity detalle) {
        String sql = """
            UPDATE detalle_pedido
            SET entregado = :entregado,
                hora_entrega = :hora_entrega,
                calificacion = :calificacion
            WHERE id_detalle_pedido = :id_detalle_pedido
              AND deleted_at IS NULL
        """;

        try (Connection con = sql2o.open()) {
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
            SELECT * FROM detalle_pedido
            WHERE id_detalle_pedido = :id
              AND deleted_at IS NULL
        """;

        try (Connection con = sql2o.open()) {
            DetallePedidoEntity detallePedido = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(DetallePedidoEntity.class);
            return Optional.ofNullable(detallePedido)
                    .orElseThrow(() -> new RuntimeException("No se encontró el detalle de pedido con id: " + id));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(Long id) {
        String sql = """
                    UPDATE detalle_pedido
                    SET deleted_at = NOW()
                    WHERE id_detalle_pedido = :id
                      AND deleted_at IS NULL
                """;
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
