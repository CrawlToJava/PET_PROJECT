package service.impl;

import entity.Order;
import entity.RentalPoint;
import entity.Scooter;
import entity.User;
import exceptions.NotAvailableException;
import lombok.AllArgsConstructor;
import repository.JPARepository;
import service.RentalPointService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RentalPointServiceImpl implements RentalPointService {
    private final JPARepository<User> userRepository;

    private final JPARepository<Scooter> scooterRepository;

    private final JPARepository<Order> orderRepository;

    private final JPARepository<RentalPoint> rentalPointRepository;

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
