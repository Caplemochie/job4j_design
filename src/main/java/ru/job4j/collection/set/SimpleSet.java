package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArray<T> set = new SimpleArray<>();

    @Override
    public boolean add(T value) {
        boolean duplicate = false;
        for (T val : set) {
            if (val == value) {
                duplicate = true;
            }
        }
        if (!duplicate) {
            set.add(value);
        }

        return !duplicate;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        for (T val : set) {
            if (val == value) {
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