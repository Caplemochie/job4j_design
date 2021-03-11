package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {

        while (row + 1 != data.length) {
            if (data[row].length == 0) {
                row++;
                continue;
            }
            return column <= data[row].length;

        }
        return column < data[row].length;

    }

    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if ((data[row].length) == 0) {
            row++;
        }

        int element = data[row][column];

        column++;
        while (row < data.length && column >= data[row].length) {
            column = 0;
            row++;
        }
        return element;
    }

}
