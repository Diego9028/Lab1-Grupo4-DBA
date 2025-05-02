package delivery.demo.controllers;

import delivery.demo.entities.ClienteEntity;
import delivery.demo.services.ClienteService;
import delivery.demo.services.TokenSevice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@CrossOrigin
@RequiredArgsConstructor
public class ClienteController {

    ClienteService clienteService;
    private final TokenSevice tokenSevice;

    @GetMapping("/con-mas-gasto")
    public ClienteEntity obtenerClienteConMasGasto() {
        return clienteService.obtenerClienteConMasGasto();
    }


}
