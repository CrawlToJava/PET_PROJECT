package application.service;

import application.entity.Model;

import java.util.List;
import java.util.Optional;


public interface ModelService {
    void save(Model model);

    void delete(Long id);

    void update(Model model);

    Optional<Model> findById(Long id);

    List<Model> findAll();
}
