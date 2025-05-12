package delivery.demo.services;

import delivery.demo.entities.CalificacionEntity;
import delivery.demo.repositories.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionService {

    private final CalificacionRepository calificacionRepository;

    @Autowired
    public CalificacionService(CalificacionRepository calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
    }

    public CalificacionEntity crear(CalificacionEntity calificacion) {
        return calificacionRepository.crear(calificacion);
    }

    public List<CalificacionEntity> findAll() {
        return calificacionRepository.getAll();
    }

    public boolean update(CalificacionEntity calificacion, Integer id) {
        return calificacionRepository.update(calificacion, id);
    }

    public void delete(Integer id) {
        calificacionRepository.delete(id);
    }
}
