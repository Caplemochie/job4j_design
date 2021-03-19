package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int size;

    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    public void add(T model) {
        array[size++] = model;
    }

    public int size() {
        return size;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        array[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size] = null;
        size--;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) array[index];
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int point = 0;

            @Override
            public boolean hasNext() {
                return point != size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[point++];
            }
        };

    }

    public static void main(String[] args) {
        SimpleArray<String> simpleArray = new SimpleArray<>(2);
        simpleArray.remove(0);
        System.out.println(simpleArray.get(0));

    }
}
