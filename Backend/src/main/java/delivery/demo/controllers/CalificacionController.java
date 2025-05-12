package delivery.demo.controllers;

import delivery.demo.entities.CalificacionEntity;
import delivery.demo.services.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calificacion")
@CrossOrigin
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @GetMapping("/")
    public List<CalificacionEntity> obtenerTodas() {
        return calificacionService.findAll();
    }

    @PostMapping("/crear")
    public ResponseEntity<CalificacionEntity> crear(@RequestBody CalificacionEntity calificacion) {
        CalificacionEntity creada = calificacionService.crear(calificacion);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@RequestBody CalificacionEntity calificacion, @PathVariable Integer id) {
        boolean actualizada = calificacionService.update(calificacion, id);
        if (actualizada) {
            return ResponseEntity.ok("Calificación actualizada correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        calificacionService.delete(id);
        return ResponseEntity.ok("Calificación eliminada.");
    }
}
