package application.service.impl;

import application.entity.User;
import application.exception.NoDataFoundException;
import application.repository.UserRepository;
import application.service.UserService;
import customspring.metadata.Autowired;
import customspring.metadata.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

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
