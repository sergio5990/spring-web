package by.academy.it.dao.impl;

import by.academy.it.dao.Dao;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Repository
public class BaseDao<T> implements Dao<T> {
    Class<T> clazz;
    @PersistenceContext
    @Getter
    private EntityManager em;

    @Override
    public T add(T t) {
        em.persist(t);
        return t;
    }
    @Override
    public T get(Serializable id) {
        return em.find(clazz, id);
    }
    @Override
    public T update(T t) {
        em.merge(t);
        return t;
    }
    @Override
    public void delete(Serializable id) {
        T t = em.find(clazz, id);
        em.remove(t);
    }
}