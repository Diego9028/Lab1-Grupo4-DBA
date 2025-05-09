package delivery.demo.services;

import delivery.demo.repositories.UrgenciaRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UrgenciaService {

    @Autowired
    private UrgenciaRepositoryImp urgenciaRepositoryImp;

    public List<Map<String, Object>> obtenerMedioPagoMasUsadoEnUrgenciasAltas() {
        return urgenciaRepositoryImp.obtenerMedioPagoMasUsadoEnUrgenciasAltas();
    }
}
