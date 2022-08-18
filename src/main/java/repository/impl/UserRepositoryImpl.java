package repository.impl;

import entity.Users;
import exceptions.NoSuchElementException;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class UserRepositoryImpl implements UserRepository {
    private final List<Users> users = new ArrayList<>();

    @Override
    public void save(Users users) {
        if (isPresent(this.users, users.getId())) {
            throw new RuntimeException("Человек с таким id уже есть");
        } else {
            this.users.add(users);
        }
    }

    @Override
    public void delete(Long id) {
        users.remove(Math.toIntExact(id));
    }

    @Override
    public Optional<Users> findById(Long id) {
        return users.stream().
                filter(user ->
                        user.getId().equals(id)).findFirst();
    }

    @Override
    public void update(Long id, Users users) {
        if (isPresent(this.users, id)) {
            Users updatedPerson = this.users.get(Math.toIntExact(id));
            updatedPerson.setFirstName(users.getFirstName());
            updatedPerson.setSecondName(users.getSecondName());
            updatedPerson.setAge(users.getAge());
            updatedPerson.setSex(users.getSex());
        } else {
            throw new NoSuchElementException("Человек не найден");
        }
    }

    public boolean isPresent(List<Users> usersList, Long id) {
        return usersList.stream().anyMatch(u -> u.getId().equals(id));
    }

    public List<Users> findAll() {
        return users;
    }

    @Override
    public List<Users> findBySecondName(String secondName) {
        return users.stream().filter(user -> user.getSecondName().equals(secondName)).collect(Collectors.toList());
    }
}
