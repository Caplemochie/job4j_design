package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] container;
    private static final int DEFAULT_CAPACITY = 10;
    private int modCount;
    private int size;

    public SimpleArray() {
        this.container = new Object[DEFAULT_CAPACITY];
        modCount = 0;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        if (container.length == size) {
            Object[] newArray = new Object[container.length * 2];
            System.arraycopy(container, 0, newArray, 0, size);
            container = newArray;
        }
        container[size] = model;
        size++;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int point;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return point != size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[point++];
            }
        };
    }
}