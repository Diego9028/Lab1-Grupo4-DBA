package delivery.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import delivery.demo.entities.CategoriaEntity;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository

public class CategoriaRepositoryImp implements  CategoriaRepository {

    @Autowired
    private final Sql2o sql2o;

    public CategoriaRepositoryImp(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public CategoriaEntity save(CategoriaEntity categoria) {
        String sql = """
        INSERT INTO categoria (nombre)
        VALUES (:nombre)
        RETURNING id_categoria
    """;

        try (Connection con = sql2o.open()){
            Long id_categoria = con.createQuery(sql)
                    .addParameter("nombre", categoria.getNombre())
                    .executeUpdate()
                    .getKey(Long.class);
            categoria.setId_categoria(id_categoria);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        return categoria;
    }
}

/*
public TokenEntity save(TokenEntity token) {
        String sql = """
        INSERT INTO TOKEN_ENTITY (token, token_type, revoked, expired, cliente_id)
        VALUES (:token, :tokenType, :revoked, :expired, :clienteId)
        RETURNING id
    """;

        try (Connection con = sql2o.open()) {
            Long id = con.createQuery(sql)
                    .addParameter("token", token.getToken())
                    .addParameter("tokenType", token.getToken_type().name())
                    .addParameter("revoked", token.isRevoked())
                    .addParameter("expired", token.isExpired())
                    .addParameter("clienteId", token.getCliente_id())
                    .executeUpdate()
                    .getKey(Long.class);
            token.setId(id);
            return token;
        }
    }
 */


/*
@Override
    public Cliente crear(Cliente cliente) {
        try (Connection con = sql2o.open()) {
            // query para crear un cliente
            String sql = "INSERT INTO cliente (nombre,direccion,email,telefono,contrasena,rol)"
                    + "VALUES (:nombre,:direccion,:email,:telefono,:contrasena,:rol)";
            con.createQuery(sql)
                    .addParameter("nombre", cliente.getNombre())
                    .addParameter("direccion", cliente.getDireccion())
                    .addParameter("email", cliente.getEmail())
                    .addParameter("telefono", cliente.getTelefono())
                    .addParameter("contrasena", cliente.getContrasena())
                    .addParameter("rol", cliente.getRol())
                    .executeUpdate(); // ejecucion de la query
            return cliente;
        } catch (Exception e) {
            System.out.println(e.getMessage()); // mensaje en caso de error
            return null;
        }
    }
 */