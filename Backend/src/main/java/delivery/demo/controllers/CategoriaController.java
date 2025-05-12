package delivery.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import delivery.demo.services.CategoriaService;
import delivery.demo.entities.CategoriaEntity;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin

public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/crear")
    public CategoriaEntity crearCategoria(@RequestBody CategoriaEntity categoria) {
        return categoriaService.crearCategoria(categoria);
    }

    @GetMapping("/")
    public List<CategoriaEntity> obtenerCategorias() {
        return categoriaService.obtenerCategorias();
    }

    @DeleteMapping("/eliminar/{id_categoria}")
    public void eliminarCategoria(@PathVariable Long id_categoria) {
        categoriaService.eliminarCategoria(id_categoria);
    }

    @PutMapping("/actualizar/{id_categoria}")
    public void actualizarCategoria(@PathVariable Long id_categoria, @RequestBody CategoriaEntity categoria) {
        categoriaService.actualizarCategoria(id_categoria, categoria);
    }
}
