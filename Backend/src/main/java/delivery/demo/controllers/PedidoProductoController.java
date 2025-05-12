package delivery.demo.controllers;

import delivery.demo.entities.PedidoProductoEntity;
import delivery.demo.repositories.PedidoProductoRepository;
import delivery.demo.services.PedidoProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidoProducto")
@CrossOrigin
@RequiredArgsConstructor

public class PedidoProductoController {

    @Autowired
    private final PedidoProductoService pedidoProductoService;

    // Metodo para obtener el detalle de un producto a partir de su id de pedido
    @GetMapping("/{id}")
    public List<PedidoProductoEntity> obtenerPedidoProductoPorId(@RequestParam Long id) {
        return pedidoProductoService.obtenerPedidoProductoPorId(id);
    }

}
