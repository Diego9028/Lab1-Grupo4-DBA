package delivery.demo.services;

import delivery.demo.entities.DetallePedidoEntity;
import delivery.demo.repositories.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public List<DetallePedidoEntity> getAll() {
        return detallePedidoRepository.getAll();
    }

    public boolean update(DetallePedidoEntity detalle) {
        return detallePedidoRepository.update(detalle);
    }

    public DetallePedidoEntity obtenerDetallePedidoPorId(Long id) {
        return detallePedidoRepository.obtenerDetallePedidoPorId(id);
    }
}
