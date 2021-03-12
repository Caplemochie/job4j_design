package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return exist() == 1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
            return data[point++];
    }

    private Integer exist() {
        int num = 0;
        for (int i = point; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                point = i;
                num++;
                break;
            }
        }
        return num;
    }
}
