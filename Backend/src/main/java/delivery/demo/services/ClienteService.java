package delivery.demo.services;

import delivery.demo.entities.ClienteEntity;
import delivery.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public ClienteEntity obtenerClienteConMasGasto() {
        return clienteRepository.findClienteQueMasGasto_2();
    }

    public List<ClienteEntity> obtenerClientes() {
        return clienteRepository.findAllClientes();
    }
}
