package delivery.demo.services;

import delivery.demo.entities.CategoriaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import delivery.demo.repositories.CategoriaRepositoryImp;

@Service

public class CategoriaService {

    @Autowired
    private CategoriaRepositoryImp categoriaRepository;

    public CategoriaEntity crearCategoria(CategoriaEntity categoria) {
        return categoriaRepository.save(categoria);
    }



}
