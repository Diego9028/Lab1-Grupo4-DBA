package delivery.demo.repositories;

import delivery.demo.entities.TokenEntity;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Optional;

@Repository
public class TokenRepositoryImp{

    private final Sql2o sql2o;

    public TokenRepositoryImp(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<TokenEntity> findAllValidTokenByUser(Long id) {
        String sql = """
        SELECT t.* FROM TOKEN_ENTITY t
        WHERE t.cliente_id = :id AND (t.expired = FALSE OR t.revoked = FALSE)
    """;

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(TokenEntity.class);
        }
    }

    public Optional<TokenEntity> findByToken(String token) {
        String sql = "SELECT * FROM TOKEN_ENTITY t WHERE t.token = :token";

        try (Connection con = sql2o.open()) {
            TokenEntity tokenEntity = con.createQuery(sql)
                    .addParameter("token", token)
                    .executeAndFetchFirst(TokenEntity.class);
            return Optional.ofNullable(tokenEntity);
        }
    }

    public Optional<TokenEntity> findById(Long id) {
        String sql = "SELECT * FROM TOKEN_ENTITY WHERE id = :id";

        try (Connection con = sql2o.open()) {
            TokenEntity tokenEntity = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(TokenEntity.class);
            return Optional.ofNullable(tokenEntity);
        }
    }

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

    public List<TokenEntity> saveAll(List<TokenEntity> tokens) {
        String sql = """
        INSERT INTO TOKEN_ENTITY (token, token_type, revoked, expired, cliente_id)
        VALUES (:token, :tokenType, :revoked, :expired, :clienteId)
        RETURNING id
    """;

        try (Connection con = sql2o.open()) {
            for (TokenEntity token : tokens) {
                Long id = con.createQuery(sql)
                        .addParameter("token", token.getToken())
                        .addParameter("tokenType", token.getToken_type().name())
                        .addParameter("revoked", token.isRevoked())
                        .addParameter("expired", token.isExpired())
                        .addParameter("clienteId", token.getCliente_id())
                        .executeUpdate()
                        .getKey(Long.class);
                token.setId(id);
            }
            return tokens;
        }
    }
}