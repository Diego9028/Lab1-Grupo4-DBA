package delivery.demo.services;

import delivery.demo.repositories.PedidoRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepositoryImp pedidoRepositoryImp;

    public List<Map<String, Object>> obtenerMasPedidosPorCategoriaUltimoMes() {
        return pedidoRepositoryImp.obtenerMasPedidosPorCategoriaUltimoMes();
    }
}
