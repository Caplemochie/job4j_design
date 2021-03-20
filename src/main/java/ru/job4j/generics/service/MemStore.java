package ru.job4j.generics.service;

import ru.job4j.generics.models.Base;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (findById(id) != null) {
            mem.set(mem.indexOf(findById(id)), model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        return mem.remove(findById(id));
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (T model : mem) {
            if (model.getId().equals(id)) {
                result = model;
                break;
            }
        }
        return result;
    }
}