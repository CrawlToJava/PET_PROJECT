package service.impl;

import entity.User;
import exceptions.NoDataFoundException;
import exceptions.NotAvailableException;
import lombok.AllArgsConstructor;
import repository.OrderRepository;
import repository.RentalPointRepository;
import repository.ScooterRepository;
import repository.UserRepository;
import service.UserService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final ScooterRepository scooterRepository;

    private final OrderRepository orderRepository;

    private final RentalPointRepository rentalPointRepository;


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
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(id);
        } else {
            throw new NoDataFoundException("Пользователя с таким id не сущестует");
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
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user;
        } else {
            throw new NoDataFoundException("Пользователя с таким id не существует");
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            throw new NoDataFoundException("Таблица с пользователями пустая");
        }
        return userList;
    }

    @Override
    public List<User> findByLastName(String lastName) {
        List<User> userList = userRepository.findByLastName(lastName);
        if (userList.isEmpty()) {
            throw new NoDataFoundException("Пользователей с такой фамилией не сущестует");
        }
        return userList;
    }
}
