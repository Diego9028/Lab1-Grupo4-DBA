package delivery.demo.repositories;

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
                SELECT * FROM producto_servicio 
                WHERE id_producto_servicio = :id AND deleted_at IS NULL
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

    public List<ProductoServicioEntity> obtenerTodos() {
        String sql = """
                SELECT * FROM producto_servicio
                WHERE deleted_at IS NULL
                """;

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(ProductoServicioEntity.class);
        }
    }

    public void actualizarProductoServicio(Long id, ProductoServicioEntity productoServicio) {
        String sql = """
                UPDATE producto_servicio
                SET stock = :stock,
                    precio = :precio,
                    id_categoria = :idCategoria,
                    id_empresa_asociada = :idEmpresaAsociada,
                    es_producto = :esProducto
                WHERE id_producto_servicio = :id
                  AND deleted_at IS NULL
                """;

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("stock", productoServicio.getStock())
                    .addParameter("precio", productoServicio.getPrecio())
                    .addParameter("idCategoria", productoServicio.getId_categoria())
                    .addParameter("idEmpresaAsociada", productoServicio.getId_empresa_asociada())
                    .addParameter("esProducto", productoServicio.getEs_producto())
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public void eliminarProductoServicio(Long id) {
        String sql = """
                UPDATE producto_servicio
                SET deleted_at = NOW()
                WHERE id_producto_servicio = :id
                  AND deleted_at IS NULL
                """;

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}