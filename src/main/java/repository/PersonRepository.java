package repository;

import entity.Person;


import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    void save(Person person);

    void delete(Long id);

    int size();

    Optional<Person> findById(Long id);

    List<Person> findAll();

    List<Person> findBySecondName(String secondName);
}