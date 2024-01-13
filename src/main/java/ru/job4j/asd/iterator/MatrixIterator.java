package ru.job4j.asd.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIterator(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (row < data.length) {
           if (data[row].length == 0) {
               while (row < data.length && data[row].length == 0) {
                   ++row;
               }
           }
            if (row < data.length && column < data[row].length) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column == data[row].length - 1) {
            int bufferRow = row;
            int bufferColumn = column;
            row++;
            column = 0;
            return data[bufferRow][bufferColumn];
        }
        return data[row][column++];
    }
    public static void main(String[] args) {
        int[][] input = {
                {}
        };
        MatrixIterator matrixIterator = new MatrixIterator(input);
        matrixIterator.next();
    }
}
