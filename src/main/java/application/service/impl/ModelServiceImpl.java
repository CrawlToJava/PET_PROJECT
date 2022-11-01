package application.service.impl;

import application.entity.Model;
import application.exception.NoDataFoundException;
import application.repository.ModelRepository;
import application.service.ModelService;
import customspring.metadata.Autowired;
import customspring.metadata.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelRepository modelRepository;

    @Override
    public void save(Model model) {
        modelRepository.save(model);
    }

    @Override
    public void delete(Long id) {
        modelRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Модель самоката с таким id не найдена"));
        modelRepository.delete(id);
    }

    @Override
    public void update(Model model) {
        modelRepository.findById(model.getId()).orElseThrow(() -> new NoDataFoundException("Модель самоката с таким id не найдена"));
        modelRepository.update(model);
    }

    @Override
    public Optional<Model> findById(Long id) {
        return Optional.ofNullable(modelRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Модели самоката с таким id не сущестует")));
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
