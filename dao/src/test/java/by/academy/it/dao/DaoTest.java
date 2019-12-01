package by.academy.it.dao;

import by.academy.it.dao.impl.PersonDaoImpl;
import by.academy.it.entity.Person;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;


@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DaoTest{

    @Autowired
    private PersonDaoImpl personDao;

    @Test
    public void addPerson() {
        Person p = new Person();
        p.setName("Yuli");
        p.setSurname("Slabko");
        p.setAge(30);
        Person persistent = personDao.add(p);
        assertNotNull(persistent.getId());
        persistent = personDao.get(persistent.getId());
        assertEquals("Person not persist", p, persistent);
    }
    
    @After
    public void deletePerson() {
        List<Person> list = personDao.getPersons();              
        int size = list.size();
        if (list.size() > 0) {
            Person persistent = list.get(0);
            personDao.delete(persistent.getId());
            assertNotSame(personDao.getPersons().size(), size);
        }
    }
}
