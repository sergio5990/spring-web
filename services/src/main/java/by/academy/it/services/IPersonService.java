package by.academy.it.services;

import by.academy.it.entity.Person;

import java.util.List;

public interface IPersonService {

    List<Person> getPersons();

    Person create(Person person);

    void delete(Person person);
}
