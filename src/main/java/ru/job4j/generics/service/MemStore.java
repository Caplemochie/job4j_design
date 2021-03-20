package ru.job4j.generics.service;

import ru.job4j.generics.models.Base;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    public int getIndex(String id) {
        int index = 0;
        boolean haveIndex = false;
        for (T model : mem) {
            if (model.getId().equals(id)) {
                haveIndex = true;
                break;
            }
            index++;
        }
        if (!haveIndex) {
            index = -1;
        }
        return index;
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = getIndex(id);
        if (index != -1) {
            mem.set(index, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = getIndex(id);
        if (index != -1) {
            mem.remove(index);
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        int index = getIndex(id);
        if (index == -1) {
            throw new NoSuchElementException();
        }
        return mem.get(index);
    }
}