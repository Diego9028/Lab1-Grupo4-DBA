package delivery.demo.services;

import delivery.demo.entities.ProductoServicioEntity;
import delivery.demo.repositories.ProductoServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServicioService {

    @Autowired
    private ProductoServicioRepository productoServicioRepository;

    // Metodo para obtener el detalle de un producto a partir de su id
    public ProductoServicioEntity obtenerProductoServicioPorId(Long id) {
        return productoServicioRepository.obtenerProductoServicioPorId(id);
    }
}
