package by.academy.it.dao;

import java.io.Serializable;

public interface Dao<T> {
    T add(T t);

    T update(T t);

    T get(Serializable id);

    void delete(Serializable id);
}
