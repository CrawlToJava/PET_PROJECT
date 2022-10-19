package service.impl;

import entity.User;
import exception.NoDataFoundException;
import lombok.AllArgsConstructor;
import repository.UserRepository;
import service.UserService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Пользователя с таким id не сущестует"));
        userRepository.delete(id);
    }

    @Override
    public void update(User user) {
        userRepository.findById(user.getId()).orElseThrow(() -> new NoDataFoundException("Пользователя с таким id не сущестует"));
        userRepository.update(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Пользователя с таким id не сущестует")));
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
