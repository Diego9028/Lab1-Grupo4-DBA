package delivery.demo.services;

import delivery.demo.entities.ProductoServicioEntity;
import delivery.demo.repositories.ProductoServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServicioService {

    @Autowired
    private ProductoServicioRepository productoServicioRepository;

    public ProductoServicioEntity obtenerProductoServicioPorId(Long id) {
        return productoServicioRepository.obtenerProductoServicioPorId(id);
    }
}