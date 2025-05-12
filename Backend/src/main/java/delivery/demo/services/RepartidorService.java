package delivery.demo.services;

import delivery.demo.entities.RepartidorEntity;
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

    public List<Map<String, Object>> obtenerDesempenoPorRepartidor() {
        return repartidorRepositoryImp.obtenerDesempenoPorRepartidor();
    }

    public List<RepartidorEntity> obtenerTodos() {
        return repartidorRepositoryImp.obtenerTodos();
    }

    public void update(RepartidorEntity repartidor) {
        repartidorRepositoryImp.update(repartidor);
    }

    public void crearRepartidor(RepartidorEntity repartidor) {
        repartidorRepositoryImp.crearRepartidor(repartidor);
    }
}
