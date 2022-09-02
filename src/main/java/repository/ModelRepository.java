package repository;

import entity.Model;

import java.util.List;
import java.util.Optional;

public interface ModelRepository {
    void save(Model model);

    void delete(Long id);

    void update(Long id, Model model);

    Optional<Model> findById(Long id);

    List<Model> findAll();
}
