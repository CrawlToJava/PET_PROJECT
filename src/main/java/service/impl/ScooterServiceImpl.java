package service.impl;

import entity.Scooter;
import exceptions.NoDataFoundException;
import exceptions.NotAvailableException;
import lombok.AllArgsConstructor;
import repository.OrderRepository;
import repository.RentalPointRepository;
import repository.ScooterRepository;
import repository.UserRepository;
import service.ScooterService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ScooterServiceImpl implements ScooterService {
    private final UserRepository userRepository;

    private final ScooterRepository scooterRepository;

    private final OrderRepository orderRepository;

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
        Optional<Scooter> scooterFromDataBase = scooterRepository.findById(scooter.getId());
        if (scooterFromDataBase.isPresent()) {
            scooterRepository.update(scooter);
        } else {
            throw new NoDataFoundException("Самоката с таким id не существует");
        }
    }

    @Override
    public Optional<Scooter> findById(Long id) {
        Optional<Scooter> scooter = scooterRepository.findById(id);
        if (scooter.isPresent()) {
            return scooter;
        } else {
            throw new NoDataFoundException("Самоката с таким id не существует");
        }
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
