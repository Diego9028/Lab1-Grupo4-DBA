package delivery.demo.repositories;

import delivery.demo.entities.TokenEntity;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Optional;

@Repository
public class TokenRepositoryImp {

    private final Sql2o sql2o;

    public TokenRepositoryImp(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<TokenEntity> findAllValidTokenByUser(Long id) {
        String sql = """
        SELECT * FROM TokenEntity t
        INNER JOIN ClienteEntity u ON t.cliente_id = u.id
        WHERE u.id = :id AND (t.expired = FALSE OR t.revoked = FALSE)
    """;

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(TokenEntity.class);
        }
    }

    public Optional<TokenEntity> findByToken(String token) {
        String sql = "SELECT * FROM TokenEntity t WHERE t.token = :token";

        try (Connection con = sql2o.open()) {
            TokenEntity tokenEntity = con.createQuery(sql)
                    .addParameter("token", token)
                    .executeAndFetchFirst(TokenEntity.class);
            return Optional.ofNullable(tokenEntity);
        }
    }

    public Optional<TokenEntity> findById(Long id) {
        String sql = "SELECT * FROM TokenEntity WHERE id = :id";

        try (Connection con = sql2o.open()) {
            TokenEntity tokenEntity = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(TokenEntity.class);
            return Optional.ofNullable(tokenEntity);
        }
    }
}
