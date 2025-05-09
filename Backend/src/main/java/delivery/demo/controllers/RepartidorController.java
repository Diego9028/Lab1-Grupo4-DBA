package delivery.demo.controllers;

import delivery.demo.services.RepartidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/repartidor")
@CrossOrigin

public class RepartidorController {
    @Autowired
    private RepartidorService repartidorService;

    @GetMapping("/top-3")
    public List<Map<String, Object>> obtenerTop3Repartidores() {
        return repartidorService.obtenerTop3Repartidores();
    }
}
