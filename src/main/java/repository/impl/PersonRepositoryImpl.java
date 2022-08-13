package repository.impl;

import entity.Person;
import exceptions.NoSuchElementException;
import repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PersonRepositoryImpl implements PersonRepository {
    private final List<Person> personList = new ArrayList<>();

    @Override
    public void save(Person person) {
        if (isPresent(personList, person.getId())) {
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
                .filter(person -> person.getSecondName().equals(secondName))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Long id, Person person) {
        if (isPresent(personList, id)) {
            Person updatedPerson = personList.get(Math.toIntExact(id));
            updatedPerson.setFirstName(person.getFirstName());
            updatedPerson.setSecondName(person.getSecondName());
            updatedPerson.setAge(person.getAge());
            updatedPerson.setSex(person.getSex());
        } else {
            throw new NoSuchElementException("Человек не найден");
        }
    }

    public boolean isPresent(List<Person> personList, Long id) {
        return personList.stream().anyMatch(p -> p.getId().equals(id));
    }

    public List<Person> findAll() {
        return personList;
    }
}
