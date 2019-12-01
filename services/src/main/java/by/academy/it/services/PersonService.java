package by.academy.it.services;

import by.academy.it.dao.PersonDao;
import by.academy.it.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonService implements IPersonService {
    @Autowired
    PersonDao personDao;

    public List<Person> getPersons() {
        return personDao.getPersons();
    }

    public Person create(Person person) {
        if (person != null) {
            return (Person) personDao.add(person);
        }
        return person;
    }

    public void delete(Person person) {
        if (person != null) {
            personDao.delete(person.getId());
        }
    }
}
