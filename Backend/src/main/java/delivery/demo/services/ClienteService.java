package delivery.demo.services;

import delivery.demo.entities.ClienteEntity;
import delivery.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import delivery.demo.repositories.ClienteRepositoryImp;

import java.util.List;
import java.util.Map;

@Service

public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteRepositoryImp clienteRepositoryImp;

    public Map<String, Object> obtenerClienteConMasGasto() {
        return clienteRepositoryImp.findClienteQueMasGasto();
    }

    public List<ClienteEntity> obtenerClientes() {
        return clienteRepositoryImp.findAllClientes();
    }

    public Long idClienteTopGasto() {
        return clienteRepositoryImp.obtenerClienteConMasGasto()
                .orElseThrow(() -> new RuntimeException("No se encontró ningún cliente"));
    }
}
