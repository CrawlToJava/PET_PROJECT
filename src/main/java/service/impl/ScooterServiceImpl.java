package service.impl;

import entity.Scooter;
import exceptions.NotAvailableException;
import lombok.AllArgsConstructor;
import repository.RentalPointRepository;
import repository.ScooterRepository;
import repository.UserRepository;
import service.ScooterService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ScooterServiceImpl implements ScooterService {
    private final ScooterRepository scooterRepository;

    private final UserRepository userRepository;

    private final RentalPointRepository rentalPointRepository;

    @Override
    public void save(Scooter scooter) {
        Optional<Scooter> scooterFromDataBase = scooterRepository.findById(scooter.getId());
        if (scooterFromDataBase.isEmpty()) {
            scooterRepository.save(scooter);
        } else {
            throw new NotAvailableException("Самокат с таким id уже есть");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Scooter> scooterFromDataBase = scooterRepository.findById(id);
        if (scooterFromDataBase.isPresent()) {
            scooterRepository.delete(id);
        } else {
            throw new NotAvailableException("Самоката с таким id не сущестует");
        }
    }

    @Override
    public void update(Scooter scooter) {
        scooterRepository.update(scooter);
    }

    @Override
    public Optional<Scooter> findById(Long id) {
        return scooterRepository.findById(id);
    }

    @Override
    public List<Scooter> findAll() {
        return scooterRepository.findAll();
    }
}
