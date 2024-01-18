package ru.job4j.asd.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    private int index = 0;

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (index == data.size()) {
            index = 0;
        }
        return data.size() != 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data.get(index++);
    }
    public static void main(String[] args) {
        CyclicIterator<Integer> iterator = new CyclicIterator<>(List.of(1));
        iterator.next();
        iterator.next();
    }
}
