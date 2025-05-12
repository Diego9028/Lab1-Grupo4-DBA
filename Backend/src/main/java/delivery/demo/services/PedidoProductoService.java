package delivery.demo.services;

import delivery.demo.entities.PedidoProductoEntity;
import delivery.demo.repositories.PedidoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PedidoProductoService {

    @Autowired
    private PedidoProductoRepository pedidoProductoRepository;

    // Metodo para obtener el pedido de un producto a partir de su id de pedido
    public List<PedidoProductoEntity> obtenerPedidoProductoPorId(Long id) {
        return pedidoProductoRepository.obtenerPedidoProductoPorId(id);
    }
}
