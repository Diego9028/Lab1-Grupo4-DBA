package delivery.demo.controllers;

import delivery.demo.entities.ClienteEntity;
import delivery.demo.services.ClienteService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
@CrossOrigin

public class ClienteController {
    ClienteService clienteService;

    @GetMapping("/con-mas-gasto")
    public ClienteEntity obtenerClienteConMasGasto() {
        return clienteService.obtenerClienteConMasGasto();
    }
}
