package by.academy.it.services;

import java.io.Serializable;

public interface IService<T> {
    void add(T t);

    void update(T t);

    T get(Serializable id);

    void delete(Serializable id);
}
