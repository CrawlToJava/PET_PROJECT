package repository.impl;

import entity.Person;
import repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PersonRepositoryImpl implements PersonRepository {
    private final List<Person> personList = new ArrayList<>();

    @Override
    public void save(Person person) {
        boolean isPresent = personList.stream().anyMatch(person1 -> person1.getId().equals(person.getId()));
        if (isPresent) {
            throw new RuntimeException("Человек с таким id уже есть");
        } else {
            personList.add(person);
        }
    }

    @Override
    public void delete(Long id) {
        personList.remove(Math.toIntExact(id));
    }

    @Override
    public int size() {
        return personList.size();
    }

    @Override
    public Optional<Person> findById(Long id) {
        return personList.stream().
                filter(person ->
                        person.getId().equals(id)).findFirst();
    }

    @Override
    public List<Person> findBySecondName(String secondName) {
        return personList
                .stream()
                .filter(person -> person.getLastName().equals(secondName))
                .collect(Collectors.toList());
    }

    public List<Person> findAll() {
        return personList;
    }
}
