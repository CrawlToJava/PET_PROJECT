package service.impl;

import entity.RentalPoint;
import entity.Scooter;
import entity.User;
import exceptions.NotAvailableException;
import lombok.AllArgsConstructor;
import repository.JPARepository;
import service.ScooterService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ScooterServiceImpl implements ScooterService {
    private final JPARepository<Scooter> scooterRepository;

    private final JPARepository<User> userRepository;

    private final JPARepository<RentalPoint> rentalPointRepository;

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
