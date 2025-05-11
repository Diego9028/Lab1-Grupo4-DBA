package delivery.demo.services;

import delivery.demo.entities.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import delivery.demo.repositories.ClienteRepository;

import java.util.List;
import java.util.Map;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Map<String, Object> obtenerClienteConMasGasto() {
        return clienteRepository.findClienteQueMasGasto();
    }

    public List<ClienteEntity> obtenerClientes() {
        return clienteRepository.findAllClientes();
    }

    public ClienteEntity idClienteTopGasto() {
        return clienteRepository.obtenerClienteConMasGasto();
    }

    public ClienteEntity findByCorreo(String correo) {
        return clienteRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("No se encontró ningún cliente con ese correo"));
    }

    public ClienteEntity save(ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }
}