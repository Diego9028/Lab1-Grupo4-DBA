package delivery.demo.repositories;

import delivery.demo.entities.PedidoProductoEntity;
import delivery.demo.entities.ProductoServicioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

@Repository

public class ProductoServicioRepository {

    @Autowired
    private Sql2o sql2o;

    public ProductoServicioRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public ProductoServicioEntity obtenerProductoServicioPorId(Long id) {
        String sql = """
                SELECT * FROM producto_servicio WHERE id_producto_servicio = :id
                """;

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(ProductoServicioEntity.class);
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
            return null;
        }
    }

}
