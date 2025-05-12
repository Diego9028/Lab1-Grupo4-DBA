package delivery.demo.controllers;

import delivery.demo.entities.ProductoServicioEntity;
import delivery.demo.services.ProductoServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productoServicio")
@CrossOrigin
@RequiredArgsConstructor

public class ProductoServicioController {

    @Autowired
    private final ProductoServicioService productoServicioService;

    // Metodo para obtener el detalle de un producto a partir de su id
    @GetMapping("/{id}")
    public ProductoServicioEntity obtenerProductoServicioPorId(@RequestParam Long id) {
        return productoServicioService.obtenerProductoServicioPorId(id);
    }
}