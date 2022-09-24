package service.impl;

import annotation.Inject;
import entity.User;
import exceptions.NotAvailableException;
import repository.OrderRepository;
import repository.RentalPointRepository;
import repository.ScooterRepository;
import repository.UserRepository;
import service.UserService;

import java.util.List;
import java.util.Optional;


public class UserServiceImpl implements UserService {
    @Inject
    private UserRepository userRepository;
    @Inject
    private ScooterRepository scooterRepository;
    @Inject
    private OrderRepository orderRepository;
    @Inject
    private RentalPointRepository rentalPointRepository;


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

    @Override
    public List<User> findByLastName(String secondName) {
        return userRepository.findBySecondName(secondName);
    }
}
