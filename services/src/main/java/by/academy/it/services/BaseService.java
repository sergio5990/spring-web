package by.academy.it.services;

import by.academy.it.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class BaseService<T> implements IService<T> {
    private Dao<T> baseDao;

    @Autowired
    public BaseService(Dao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void add(T t) {
        baseDao.add(t);
    }

    @Override
    public void update(T t) {
        baseDao.update(t);
    }

    @Override
    public T get(Serializable id) {
        return baseDao.get(id);
    }

    @Override
    public void delete(Serializable id) {
        baseDao.delete(id);
    }
}
