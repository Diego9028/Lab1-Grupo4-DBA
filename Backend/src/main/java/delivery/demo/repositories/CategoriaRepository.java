package delivery.demo.repositories;

import delivery.demo.entities.CategoriaEntity;

import java.util.List;

public interface CategoriaRepository {
    public CategoriaEntity save(CategoriaEntity categoria);

    public List<CategoriaEntity> findAll();

    public void deleteById(Long id_categoria);

    public void updateById(Long id_categoria, CategoriaEntity categoria);
}
