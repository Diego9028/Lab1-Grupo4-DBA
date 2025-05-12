package delivery.demo.controllers;

import delivery.demo.entities.UrgenciaEntity;
import delivery.demo.services.UrgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/urgencia")
@CrossOrigin

public class UrgenciaController {
    @Autowired
    private UrgenciaService urgenciaService;

    @GetMapping("/medio-pago-mas-usado")
    public List<Map<String, Object>> obtenerMedioPagoMasUsadoEnUrgenciasAltas() {
        return urgenciaService.obtenerMedioPagoMasUsadoEnUrgenciasAltas();
    }

    // Crear una nueva urgencia
    @PostMapping("/crear")
    public void createUrgencia(@RequestBody UrgenciaEntity urgencia) {
        urgenciaService.createUrgencia(urgencia);
    }

    // Actualizar una urgencia existente
    @PutMapping("/actualizar/{id}")
    public void updateUrgencia(@PathVariable("id") Long idUrgencia, @RequestBody UrgenciaEntity urgencia) {
        urgencia.setId_urgencia(idUrgencia); // Asegurarse de que el id se pase correctamente
        urgenciaService.updateUrgencia(urgencia);
    }

    // Obtener todas las urgencias
    @GetMapping("/todos")
    public List<UrgenciaEntity> getAllUrgencias() {
        return urgenciaService.getAllUrgencias();
    }

    // Eliminar una urgencia
    @DeleteMapping("/eliminar/{id}")
    public void deleteUrgencia(@PathVariable("id") Long idUrgencia) {
        urgenciaService.deleteUrgencia(idUrgencia);
    }
}
