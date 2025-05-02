package delivery.demo.repositories;

import delivery.demo.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    @Query(value = """
      select t from TokenEntity t inner join ClienteEntity u\s
      on t.cliente.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<TokenEntity> findAllValidTokenByUser(Long id);

    @Query("SELECT t FROM TokenEntity t WHERE t.token = :token")
    Optional<TokenEntity> findByToken(@Param("token") String token);

    Optional<TokenEntity> findById(Long aLong);
}
