package delivery.demo.controllers;

import delivery.demo.entities.DetallePedidoEntity;
import delivery.demo.services.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detallePedido")
@CrossOrigin
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping("/")
    public List<DetallePedidoEntity> getAll() {
        return detallePedidoService.getAll();
    }

    @PutMapping("/update")
    public boolean update(@RequestBody DetallePedidoEntity detalle) {
        return detallePedidoService.update(detalle);
    }

}

