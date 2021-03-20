package ru.job4j.generics.service;

import ru.job4j.generics.models.Base;

public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
