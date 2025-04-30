package delivery.demo.Services;

import delivery.demo.Entities.ClienteEntity;
import delivery.demo.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public ClienteEntity obtenerClienteConMasGasto() {
        return clienteRepository.findClienteQueMasGasto();
    }
}
