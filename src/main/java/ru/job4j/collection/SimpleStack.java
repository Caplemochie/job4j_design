package ru.job4j.collection;

import ru.job4j.collection.list.ForwardLinked;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();
    private int size = 0;

    public int size() {
        return size;
    }

    public T pop() {
        size--;
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
        size++;

    }
}