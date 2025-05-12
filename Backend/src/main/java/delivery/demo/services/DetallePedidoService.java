package delivery.demo.services;

import delivery.demo.entities.DetallePedidoEntity;
import delivery.demo.repositories.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository DetallePedidoRepository;

    // Metodo para obtener el detalle de un pedido a partir de su id
    public DetallePedidoEntity obtenerDetallePedidoPorId(Long id) {
        return DetallePedidoRepository.obtenerDetallePedidoPorId(id);
    }
}
