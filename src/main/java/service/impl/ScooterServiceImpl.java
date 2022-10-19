package service.impl;

import entity.Scooter;
import exception.NoDataFoundException;
import lombok.AllArgsConstructor;
import repository.ScooterRepository;
import service.ScooterService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ScooterServiceImpl implements ScooterService {

    private final ScooterRepository scooterRepository;

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
