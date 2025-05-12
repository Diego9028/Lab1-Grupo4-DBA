package delivery.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import delivery.demo.entities.CategoriaEntity;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository

public class CategoriaRepositoryImp{

    @Autowired
    private final Sql2o sql2o;

    public CategoriaRepositoryImp(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public CategoriaEntity save(CategoriaEntity categoria) {
        String sql = """
                    INSERT INTO categoria (nombre)
                    VALUES (:nombre)
                    RETURNING id_categoria
                """;
        try (Connection con = sql2o.open()) {
            Long id = con.createQuery(sql)
                    .addParameter("nombre", categoria.getNombre())
                    .executeUpdate()
                    .getKey(Long.class);
            categoria.setId_categoria(id);
            return categoria;
        }
    }

    public List<CategoriaEntity> findAll() {
        String sql = """
            SELECT * FROM categoria
            WHERE deleted_at IS NULL
            """;
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(CategoriaEntity.class); // ejecucion de la query
        } catch (Exception e) {
            System.out.println(e.getMessage()); // mensaje en caso de error
        }
        return null;
    }

    public void delete(Long id_categoria) {
        String sql = """
            UPDATE categoria
               SET deleted_at = NOW()
             WHERE id_categoria = :id_categoria
               AND deleted_at IS NULL
        """;
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id_categoria", id_categoria)
                    .executeUpdate();
        }
    }

    public void updateById(Long id_categoria, CategoriaEntity categoria) {
        String sql = """
            UPDATE categoria
               SET nombre = :nombre
             WHERE id_categoria = :id_categoria
               AND deleted_at IS NULL
        """;
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("nombre", categoria.getNombre())
                    .addParameter("id_categoria", id_categoria)
                    .executeUpdate();
        }
    }
}