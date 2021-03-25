package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArray<T> set = new SimpleArray<>();

    @Override
    public boolean add(T value) {
        boolean result = false;
        if (!contains(value)) {
            set.add(value);
            result = true;
        }

        return result;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        for (T val : set) {
            if (Objects.equals(val, value)) {
                result = true;
                break;
            }
        }

        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}