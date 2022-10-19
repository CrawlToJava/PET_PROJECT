package service.impl;

import entity.Model;
import exceptions.NoDataFoundException;
import exceptions.NotAvailableException;
import lombok.AllArgsConstructor;
import repository.ModelRepository;
import service.ModelService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;

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
        Optional<Model> model = modelRepository.findById(id);
        if (model.isPresent()) {
            modelRepository.delete(id);
        } else {
            throw new NotAvailableException("Модели самоката с таким id не сущестует");
        }
    }

    @Override
    public void update(Model model) {
        Optional<Model> modelFromDataBase = modelRepository.findById(model.getId());
        if (modelFromDataBase.isPresent()) {
            modelRepository.update(model);
        } else {
            throw new NotAvailableException("Модели самоката с таким id не сущестует");
        }
    }

    @Override
    public Optional<Model> findById(Long id) {
        Optional<Model> model = modelRepository.findById(id);
        if (model.isPresent()) {
            return model;
        } else {
            throw new NoDataFoundException("Модели самоката с таким id не существует");
        }
    }

    @Override
    public List<Model> findAll() {
        List<Model> modelList = modelRepository.findAll();
        if (modelList.isEmpty()) {
            throw new NoDataFoundException("Таблица с моделями самоката пустая");
        }
        return modelList;
    }
}
