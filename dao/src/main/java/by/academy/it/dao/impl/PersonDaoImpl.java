package by.academy.it.dao.impl;

import by.academy.it.dao.PersonDao;
import by.academy.it.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDaoImpl extends BaseDao<Person> implements PersonDao<Person> {

    public PersonDaoImpl(){
        super();
        clazz = Person.class;
    }

    @Override
    public List<Person> getPersons() {
        return getEm().createQuery("from Person").getResultList();
    }
}
