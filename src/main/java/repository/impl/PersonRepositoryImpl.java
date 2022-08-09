package repository.impl;

import entity.Person;
import repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PersonRepositoryImpl implements PersonRepository {
    private final List<Person> personList = new ArrayList<>();

    @Override
    public void add(Person person) {
        personList.add(Math.toIntExact(person.getId()),person);
    }

    @Override
    public void delete(Long id) {
        personList.remove(Math.toIntExact(id));
    }

    @Override
    public int numberOfRecords() {
        return personList.size();
    }

    @Override
    public void searchPersonByID(Long id) {
        Optional<Person> matchingObject = personList.stream().
                filter(p -> p.getId()==id).
                findFirst();
        System.out.println(matchingObject);
    }

    @Override
    public void searchPersonBySecondName(String secondName) {
        Optional<Person> matchingObject = personList.stream().
                filter(p -> p.getSecondName().equals(secondName)).
                findFirst();
        System.out.println(matchingObject);
    }

    @Override
    public void searchByAllFields(String firstName, String secondName, int age, String sex, Long id) {
        Optional<Person> matchingObject = personList.stream().
                filter(p -> p.getFirstName().equals(firstName) && p.getSecondName().equals(secondName) && p.getAge() == age && p.getSex().equals(sex) && p.getId() == id).
                findFirst();
        System.out.println(matchingObject);
    }


    public List<Person> getPersonList() {
        return personList;
    }
}
