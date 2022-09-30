package service.impl;

import entity.Order;
import entity.RentalPoint;
import entity.Scooter;
import entity.User;
import exceptions.NotAvailableException;
import lombok.AllArgsConstructor;
import repository.JPARepository;
import service.UserService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final JPARepository<User> userRepository;

    private final JPARepository<Scooter> scooterRepository;

    private final JPARepository<Order> orderRepository;

    private final JPARepository<RentalPoint> rentalPointRepository;

    @Override
    public void save(User user) {
        Optional<User> userFromDataBase = userRepository.findById(user.getId());
        if (userFromDataBase.isEmpty()) {
            userRepository.save(user);
        } else {
            throw new NotAvailableException("Пользователь с таким id уже есть");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<User> userFromDataBase = userRepository.findById(id);
        if (userFromDataBase.isPresent()) {
            userRepository.delete(id);
        } else {
            throw new NotAvailableException("Пользователя с таким id не сущестует");
        }
    }

    @Override
    public void update(User user) {
        Optional<User> userFromDataBase = userRepository.findById(user.getId());
        if (userFromDataBase.isPresent()) {
            userRepository.update(user);
        } else {
            throw new NotAvailableException("Пользователя с таким id не сущестует");
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /*@Override
    public List<User> findByLastName(String secondName) {
        return userRepository.findBySecondName(secondName);
    }*/
}
