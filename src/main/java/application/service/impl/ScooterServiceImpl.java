package application.service.impl;

import application.entity.Scooter;
import application.exception.NoDataFoundException;
import application.repository.ScooterRepository;
import application.service.ScooterService;
import customspring.metadata.Autowired;
import customspring.metadata.Service;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Service
@Setter
public class ScooterServiceImpl implements ScooterService {
    @Autowired
    private ScooterRepository scooterRepository;

    @Override
    public void save(Scooter scooter) {
        scooterRepository.save(scooter);
    }

    @Override
    public void delete(Long id) {
        scooterRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Самоката с таким id не сущестует"));
        scooterRepository.delete(id);
    }

    @Override
    public void update(Scooter scooter) {
        scooterRepository.findById(scooter.getId()).orElseThrow(() -> new NoDataFoundException("Самоката с таким id не сущестует"));
        scooterRepository.update(scooter);
    }

    @Override
    public Optional<Scooter> findById(Long id) {
        return Optional.ofNullable(scooterRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Самоката с таким id не сущестует")));
    }

    @Override
    public List<Scooter> findAll() {
        List<Scooter> scooterList = scooterRepository.findAll();
        if (scooterList.isEmpty()) {
            throw new NoDataFoundException("Таблица с самокатами пустая");
        }
        return scooterList;
    }
}
