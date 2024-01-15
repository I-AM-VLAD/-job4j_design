package ru.job4j.asd.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Collections;

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean bool = false;
        if (data.hasNext()) {
            if (!cursor.hasNext()) {
                cursor = data.next();
                if (cursor == Collections.emptyIterator()) {
                    while (data.hasNext() && cursor == Collections.emptyIterator()) {
                        cursor = data.next();
                    }
                }
            }
            if (cursor.hasNext()) {
                bool = true;
            }
        }
        if (!data.hasNext() && cursor.hasNext()) {
            bool = true;
        }
        return bool;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T a = cursor.next();
        return a;
    }

    public static void main(String[] args) {

    }
}
