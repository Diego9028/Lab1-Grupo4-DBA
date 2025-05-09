package delivery.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import delivery.demo.services.PedidoService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedido")
@CrossOrigin
@RequiredArgsConstructor
public class PedidoController {

    @Autowired
    private final PedidoService pedidoService;

    @GetMapping("/mas-pedidos-por-categoria")
    public List<Map<String, Object>> obtenerMasPedidosPorCategoriaUltimoMes() {
        return pedidoService.obtenerMasPedidosPorCategoriaUltimoMes();
    }

    @GetMapping("/tiempos-promedio-entrega")
    public List<Map<String, Object>> obtenerTiemposPromedioEntrega() {
        return pedidoService.obtenerTiemposPromedioEntrega();
    }
}

