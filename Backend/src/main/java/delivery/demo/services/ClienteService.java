package delivery.demo.services;

import delivery.demo.entities.ClienteEntity;
import delivery.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import delivery.demo.repositories.ClienteRepositoryImp;

import java.util.List;

@Service

public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteRepositoryImp clienteRepositoryImp;

    public ClienteEntity obtenerClienteConMasGasto() {
        return clienteRepository.findClienteQueMasGasto_2();
    }

    public List<ClienteEntity> obtenerClientes() {
        return clienteRepositoryImp.findAllClientes();
    }

    public Long idClienteTopGasto() {
        return clienteRepositoryImp.obtenerClienteConMasGasto()
                .orElseThrow(() -> new RuntimeException("No se encontró ningún cliente"));
    }
}
