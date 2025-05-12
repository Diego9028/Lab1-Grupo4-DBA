package delivery.demo.controllers;

import delivery.demo.entities.ProductoServicioEntity;
import delivery.demo.services.ProductoServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Obtener todos los productos/servicios
    @GetMapping("/")
    public List<ProductoServicioEntity> obtenerTodos() {
        return productoServicioService.obtenerTodos();
    }

    // Actualizar un producto/servicio
    @PutMapping("/{id}")
    public void actualizarProductoServicio(@PathVariable Long id, @RequestBody ProductoServicioEntity productoServicio) {
        productoServicioService.actualizarProductoServicio(id, productoServicio);
    }

    // Eliminar un producto/servicio
    @DeleteMapping("/{id}")
    public void eliminarProductoServicio(@PathVariable Long id) {
        productoServicioService.eliminarProductoServicio(id);
    }
}