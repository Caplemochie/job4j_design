package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<K, V> implements Iterable<K> {

    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Node<K, V>[] table;
    private int size;
    private int modCount;

    public SimpleHashMap() {
        this(DEFAULT_INITIAL_CAPACITY);
        modCount = 0;
    }

    public SimpleHashMap(int initialCapacity) {
        modCount = 0;
        if (initialCapacity < 0 && initialCapacity % 2 != 0) {
            throw new IllegalArgumentException();
        }
        reSize(initialCapacity);
    }

    
    private int hash(K key) {
        int h = 0;
        if (key != null) {
            h = key.hashCode();
            h ^= h >>> 16;
        }
        return h;
    }

    private int index(int hash, int length) {
        return hash & (length - 1);
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        int i = index(hash(key), table.length);
        if (table[i] == null) {
            table[i] = new Node<>(key, value);
            size++;
            modCount++;
            result = true;
            if (size >= DEFAULT_LOAD_FACTOR * table.length) {
                reSize(table.length << 1);
            }
        } else {
            if (key != null && key.equals(table[i].key)) {
                table[i] = new Node<>(key, value);
                modCount++;
                result = true;
            }
        }
        return result;
    }

    private void reSize(int newSize) {
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[newSize];
        if (table != null) {
            for (Node<K, V> node : table) {
                if (node != null) {
                    int i = index(hash(node.key), newSize);
                    newTable[i] = node;
                }
            }
        }
        table = newTable;
    }

    public V get(K key) {
        int i = index(hash(key), table.length);
        Node<K, V> node = table[i];
        return node != null && Objects.equals(key, node.key) ? node.value : null;
    }

    public boolean delete(K key) {
        boolean result = false;
        int i = index(hash(key), table.length);
        Node<K, V> node = table[i];
        if (node != null && key.equals(node.key)) {
            table[i] = null;
            modCount++;
            result = true;
        }
        return result;
    }

    public int size() {
        return table.length;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            final int expectedModCount = modCount;
            private int point;
            private int count;

            @Override
            public boolean hasNext() {
                for (int i = point; i < table.length; i++) {
                    if (table[i] != null) {
                        point = i;
                        break;
                    }
                }
                return count < size;
            }

            @Override
            public K next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                count++;
                return (K) table[point++];
            }
        };
    }

    private static class Node<K, V> {

        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}