package delivery.demo.services;

import delivery.demo.repositories.RepartidorRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RepartidorService {

    @Autowired
    private RepartidorRepositoryImp repartidorRepositoryImp;

    public List<Map<String, Object>> obtenerTop3Repartidores() {
        return repartidorRepositoryImp.obtenerTop3Repartidores();
    }
}
