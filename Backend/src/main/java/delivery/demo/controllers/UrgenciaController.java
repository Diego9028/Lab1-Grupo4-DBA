package delivery.demo.controllers;

import delivery.demo.services.UrgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
