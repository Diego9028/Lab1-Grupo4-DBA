package delivery.demo.repositories;

import delivery.demo.entities.ClienteEntity;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ClienteRepository {

    ClienteEntity obtenerClienteConMasGasto();

    Map<String, Object> findClienteQueMasGasto();

    Optional<ClienteEntity> findByCorreo(String correo);

    List<ClienteEntity> findAllClientes();

    ClienteEntity save(ClienteEntity cliente);

    void delete(Long id);

}
