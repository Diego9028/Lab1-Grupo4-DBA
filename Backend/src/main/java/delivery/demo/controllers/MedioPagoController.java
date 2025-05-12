package delivery.demo.controllers;

import delivery.demo.entities.MedioPagoEntity;
import delivery.demo.services.MedioPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mediopago")
@CrossOrigin

public class MedioPagoController {

    @Autowired
    private MedioPagoService medioPagoService;

    @PostMapping("/create")
    public ResponseEntity<MedioPagoEntity> crearMedioPago(@RequestBody MedioPagoEntity medioPago) {
        MedioPagoEntity nuevoMedioPago = medioPagoService.crearMedioPago(medioPago);
        return ResponseEntity.ok(nuevoMedioPago);
    }

    @GetMapping("/")
    public List<MedioPagoEntity> obtenerTodosLosMediosPago() {
        return medioPagoService.obtenerTodosLosMediosPago();
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarMedioPago(@PathVariable Long id) {
        medioPagoService.eliminarMedioPago(id);
    }

    @PutMapping("/update/{id}")
    public void actualizarMedioPago(@PathVariable Long id, @RequestBody MedioPagoEntity medioPago) {
        medioPagoService.actualizarMedioPago(id, medioPago);
    }
}
