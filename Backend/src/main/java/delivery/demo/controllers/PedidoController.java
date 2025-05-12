package delivery.demo.controllers;

import delivery.demo.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @PostMapping("/crear")
    public ResponseEntity<Long> crearPedido(@RequestBody Map<String, Object> body) {
        Long idUrgencia   = ((Number) body.get("idUrgencia")).longValue();
        Long idRepartidor = ((Number) body.get("idRepartidor")).longValue();
        Long idCliente    = ((Number) body.get("idCliente")).longValue();
        Long idMedioPago  = ((Number) body.get("idMedioPago")).longValue();

        @SuppressWarnings("unchecked")
        List<Number> productosRaw = (List<Number>) body.get("productos");
        List<Long> productos = productosRaw.stream().map(Number::longValue).toList();

        @SuppressWarnings("unchecked")
        List<Number> cantidadesRaw = (List<Number>) body.get("cantidades");
        List<Integer> cantidades = cantidadesRaw.stream().map(Number::intValue).toList();

        Long idPedido = pedidoService.registrarPedidoConProductos(
                idUrgencia, idRepartidor, idCliente, idMedioPago, productos, cantidades
        );

        return ResponseEntity.ok(idPedido);
    }

    @PostMapping("/confirmar")
    public ResponseEntity<String> confirmarPedido(@RequestBody Map<String, Object> body) {
        Long idPedido = ((Number) body.get("idPedido")).longValue();
        pedidoService.confirmarPedido(idPedido);
        return ResponseEntity.ok("Pedido confirmado");
    }

    @PostMapping("/cambiar-estado")
    public ResponseEntity<Long> cambiarEstado(@RequestBody Map<String, Object> body) {
        Long idPedido = ((Number) body.get("idPedido")).longValue();
        boolean nuevoEstado = (boolean) body.get("nuevoEstado");

        boolean resultado = pedidoService.cambiarEstadoPedidoPorFuncion(idPedido, nuevoEstado);

        return ResponseEntity.ok(resultado ? idPedido : -1L);
    }   
}
