package delivery.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import delivery.demo.entities.CategoriaEntity;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository

public class CategoriaRepositoryImp implements  CategoriaRepository {

    @Autowired
    private final Sql2o sql2o;

    public CategoriaRepositoryImp(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public CategoriaEntity save(CategoriaEntity categoria) {
        try (Connection con = sql2o.open()) {
            // query para crear una categoria
            String sql = "INSERT INTO categoria (nombre) "
                    + "VALUES (:nombre)";
            con.createQuery(sql)
                    .addParameter("nombre", categoria.getNombre())
                    .executeUpdate(); // ejecucion de la query
        } catch (Exception e) {
            System.out.println(e.getMessage()); // mensaje en caso de error
        }

        return categoria;
    }

    @Override
    public List<CategoriaEntity> findAll() {
        try (Connection con = sql2o.open()) {
            // query para obtener todas las categorias
            String sql = "SELECT * FROM categoria";
            return con.createQuery(sql)
                    .executeAndFetch(CategoriaEntity.class); // ejecucion de la query
        } catch (Exception e) {
            System.out.println(e.getMessage()); // mensaje en caso de error
        }
        return null;
    }

    @Override
    public void deleteById(Long id_categoria) {
        try (Connection con = sql2o.open()) {
            // query para eliminar una categoria
            String sql = "DELETE FROM categoria WHERE id_categoria = :id_categoria";
            con.createQuery(sql)
                    .addParameter("id_categoria", id_categoria)
                    .executeUpdate(); // ejecucion de la query
        } catch (Exception e) {
            System.out.println(e.getMessage()); // mensaje en caso de error
        }
    }

    @Override
    public void updateById(Long id_categoria, CategoriaEntity categoria) {
        try (Connection con = sql2o.open()) {
            // query para actualizar una categoria
            String sql = "UPDATE categoria SET nombre = :nombre WHERE id_categoria = :id_categoria";
            con.createQuery(sql)
                    .addParameter("nombre", categoria.getNombre())
                    .addParameter("id_categoria", id_categoria)
                    .executeUpdate(); // ejecucion de la query
        } catch (Exception e) {
            System.out.println(e.getMessage()); // mensaje en caso de error
        }
    }
}

