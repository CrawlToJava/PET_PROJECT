package repository;

import entity.Person;

public interface PersonRepository {
    void add(Person person);
    void delete(Long id);
    int numberOfRecords();
    void searchPersonByID(Long id);
    void searchPersonBySecondName(String secondName);
    void searchByAllFields(String firstName, String secondName, int age, String sex,Long id);



}
