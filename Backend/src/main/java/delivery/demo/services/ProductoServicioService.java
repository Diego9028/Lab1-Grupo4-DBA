package delivery.demo.services;

import delivery.demo.entities.ProductoServicioEntity;
import delivery.demo.repositories.ProductoServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicioService {

    @Autowired
    private ProductoServicioRepository productoServicioRepository;

    public ProductoServicioEntity obtenerProductoServicioPorId(Long id) {
        return productoServicioRepository.obtenerProductoServicioPorId(id);
    }

    public List<ProductoServicioEntity> obtenerTodos() {
        return productoServicioRepository.obtenerTodos();
    }

    public void actualizarProductoServicio(Long id, ProductoServicioEntity productoServicio) {
        productoServicioRepository.actualizarProductoServicio(id, productoServicio);
    }

    public void eliminarProductoServicio(Long id) {
        productoServicioRepository.eliminarProductoServicio(id);
    }
}