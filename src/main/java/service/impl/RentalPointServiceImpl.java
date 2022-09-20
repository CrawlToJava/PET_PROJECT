package service.impl;

import entity.RentalPoint;
import exceptions.NotAvailableException;
import lombok.AllArgsConstructor;
import repository.OrderRepository;
import repository.RentalPointRepository;
import repository.ScooterRepository;
import repository.UserRepository;
import service.RentalPointService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RentalPointServiceImpl implements RentalPointService {
    private final UserRepository userRepository;

    private final ScooterRepository scooterRepository;

    private final OrderRepository orderRepository;

    private final RentalPointRepository rentalPointRepository;

    @Override
    public void save(RentalPoint rentalPoint) {
        Optional<RentalPoint> rentalPointFromDataBase = rentalPointRepository.findById(rentalPoint.getId());
        if (rentalPointFromDataBase.isEmpty()) {
            rentalPointRepository.save(rentalPoint);
        } else {
            throw new NotAvailableException("Точка проката с таким id уже есть");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<RentalPoint> rentalFromDataBase = rentalPointRepository.findById(id);
        if (rentalFromDataBase.isPresent()) {
            rentalPointRepository.delete(id);
        } else {
            throw new NotAvailableException("Точки проката с таким id не сущестует");
        }
    }

    @Override
    public void update(RentalPoint rentalPoint) {
        Optional<RentalPoint> rentalPointFromDataBase = rentalPointRepository.findById(rentalPoint.getId());
        if (rentalPointFromDataBase.isPresent()) {
            rentalPointRepository.update(rentalPoint);
        } else {
            throw new NotAvailableException("Точки проката с таким id не сущестует");
        }
    }

    @Override
    public Optional<RentalPoint> findById(Long id) {
        return rentalPointRepository.findById(id);
    }

    @Override
    public List<RentalPoint> findAll() {
        return rentalPointRepository.findAll();
    }
}
