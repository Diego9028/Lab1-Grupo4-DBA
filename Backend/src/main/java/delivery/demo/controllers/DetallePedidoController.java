package delivery.demo.controllers;

import delivery.demo.entities.DetallePedidoEntity;
import delivery.demo.services.DetallePedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/detallePedido")
@CrossOrigin
@RequiredArgsConstructor

public class DetallePedidoController {

    @Autowired
    private final DetallePedidoService DetallePedidoService;

    // Metodo para obtener el detalle de un pedido a partir de su id
    @GetMapping("/{id}")
    public DetallePedidoEntity obtenerDetallePedidoPorId(@RequestParam Long id) {
        return DetallePedidoService.obtenerDetallePedidoPorId(id);
    }

}
