package service.impl;

import annotation.Inject;
import entity.Model;
import exceptions.NotAvailableException;
import repository.ModelRepository;
import service.ModelService;

import java.util.List;
import java.util.Optional;


public class ModelServiceImpl implements ModelService {
    @Inject
    private ModelRepository modelRepository;

    @Override
    public void save(Model model) {
        Optional<Model> modelFromDataBase = modelRepository.findById(model.getId());
        if (modelFromDataBase.isEmpty()) {
            modelRepository.save(model);
        } else {
            throw new NotAvailableException("Модель самоката с таким id уже есть");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Model> modelFromDataBase = modelRepository.findById(id);
        if (modelFromDataBase.isPresent()) {
            modelRepository.delete(id);
        } else {
            throw new NotAvailableException("Модели самоката с таким id не сущестует");
        }
    }

    @Override
    public void update(Model model) {
        modelRepository.update(model);
    }

    @Override
    public Optional<Model> findById(Long id) {
        return modelRepository.findById(id);
    }

    @Override
    public List<Model> findAll() {
        return modelRepository.findAll();
    }
}
