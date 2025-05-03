package delivery.demo.controllers;

import delivery.demo.entities.ClienteEntity;
import delivery.demo.services.ClienteService;
import delivery.demo.services.TokenSevice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin
@RequiredArgsConstructor
public class ClienteController {

    ClienteService clienteService;

    @GetMapping("/con-mas-gasto")
    public ClienteEntity obtenerClienteConMasGasto() {
        return clienteService.obtenerClienteConMasGasto();
    }

    @GetMapping("/")
    public List<ClienteEntity> obtenerClientes() {
        return clienteService.obtenerClientes();
    }


}
