package delivery.demo.services;

import delivery.demo.entities.CategoriaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import delivery.demo.repositories.CategoriaRepositoryImp;

import java.util.List;

@Service

public class CategoriaService {

    @Autowired
    private CategoriaRepositoryImp categoriaRepository;

    public CategoriaEntity crearCategoria(CategoriaEntity categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<CategoriaEntity> obtenerCategorias() {
        return categoriaRepository.findAll();
    }

    public void eliminarCategoria(Long id_categoria) {
        categoriaRepository.delete(id_categoria);
    }

    public void actualizarCategoria(Long id_categoria, CategoriaEntity categoria) {
        categoriaRepository.updateById(id_categoria, categoria);
    }
}
