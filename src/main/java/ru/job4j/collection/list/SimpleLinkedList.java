package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int size = 0;
    private Node<E> first;
    private Node<E> last;
    private int modCount;

    public SimpleLinkedList() {
        modCount = 0;
    }

    private static class Node<E> {
        final E item;
        Node<E> next;

        Node(E element) {
            this.item = element;
            next = null;
        }

    }

    @Override
    public void add(E value) {
        Node<E> node = new Node<>(value);
        if (first == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            final int expectedModCount = modCount;
            private Node<E> newNode = first;

            @Override
            public boolean hasNext() {
                return newNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                E result = newNode.item;
                newNode = newNode.next;

                return result;
            }
        };
    }
}