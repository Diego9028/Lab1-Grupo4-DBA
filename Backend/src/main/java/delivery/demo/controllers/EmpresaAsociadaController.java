package delivery.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import delivery.demo.services.EmpresaAsociadaService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/empresaAsociada")
@CrossOrigin
@RequiredArgsConstructor
public class EmpresaAsociadaController {

    @Autowired
    private final EmpresaAsociadaService empresaAsociadaService;

    @GetMapping("/entregas-fallidas")
    public List<Map<String, Object>> obtenerEntregasFallidasPorEmpresa(){
        return empresaAsociadaService.obtenerEntregasFallidasPorEmpresa();
    }
}
