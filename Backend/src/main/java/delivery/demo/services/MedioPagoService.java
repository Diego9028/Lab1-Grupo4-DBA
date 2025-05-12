package delivery.demo.services;

import delivery.demo.entities.MedioPagoEntity;
import delivery.demo.repositories.MedioPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MedioPagoService {

    @Autowired
    private MedioPagoRepository medioPagoRepository;

    public MedioPagoEntity crearMedioPago(MedioPagoEntity medioPago) {
        return medioPagoRepository.save(medioPago);
    }

    public List<MedioPagoEntity> obtenerTodosLosMediosPago() {
        return medioPagoRepository.findAll();
    }

    public void eliminarMedioPago(Long id) {
        medioPagoRepository.delete(id);
    }

    public void actualizarMedioPago(Long id, MedioPagoEntity medioPago) {
        medioPagoRepository.update(id, medioPago);
    }
}
