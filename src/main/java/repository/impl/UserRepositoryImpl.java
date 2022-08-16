package repository.impl;

import entity.User;
import exceptions.NoSuchElementException;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class UserRepositoryImpl implements UserRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        if (isPresent(users, user.getId())) {
            throw new RuntimeException("Человек с таким id уже есть");
        } else {
            users.add(user);
        }
    }

    @Override
    public void delete(Long id) {
        users.remove(Math.toIntExact(id));
    }

    @Override
    public int size() {
        return users.size();
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream().
                filter(user ->
                        user.getId().equals(id)).findFirst();
    }



    @Override
    public void update(Long id, User user) {
        if (isPresent(users, id)) {
            User updatedPerson = users.get(Math.toIntExact(id));
            updatedPerson.setFirstName(user.getFirstName());
            updatedPerson.setSecondName(user.getSecondName());
            updatedPerson.setAge(user.getAge());
            updatedPerson.setSex(user.getSex());
        } else {
            throw new NoSuchElementException("Человек не найден");
        }
    }

    public boolean isPresent(List<User> userList, Long id) {
        return userList.stream().anyMatch(u -> u.getId().equals(id));
    }

    public List<User> findAll() {
        return users;
    }

    @Override
    public List<User> findBySecondName(String secondName) {
        return users.stream().filter(user -> user.getSecondName().equals(secondName)).collect(Collectors.toList());
    }
}
