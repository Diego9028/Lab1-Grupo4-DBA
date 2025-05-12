package delivery.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import delivery.demo.services.CategoriaService;
import delivery.demo.entities.CategoriaEntity;

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
}
