package delivery.demo.services;

import delivery.demo.entities.UrgenciaEntity;
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

    // Crear una nueva urgencia
    public void createUrgencia(UrgenciaEntity urgencia) {
        urgenciaRepositoryImp.create(urgencia);
    }

    // Actualizar una urgencia existente
    public void updateUrgencia(UrgenciaEntity urgencia) {
        urgenciaRepositoryImp.update(urgencia);
    }

    // Obtener todas las urgencias
    public List<UrgenciaEntity> getAllUrgencias() {
        return urgenciaRepositoryImp.getAll();
    }

    // Eliminar una urgencia
    public void deleteUrgencia(Long idUrgencia) {
        urgenciaRepositoryImp.delete(idUrgencia);
    }
}
