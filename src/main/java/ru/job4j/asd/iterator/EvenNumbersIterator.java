package ru.job4j.asd.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int var = 1;
        while (index < data.length) {
            if (data[index++] % 2 == 0) {
                --index;
                var = data[index];
                break;
            }
        }
        return var % 2 == 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

    public static void main(String[] args) {
        EvenNumbersIterator evenNumbersIterator = new EvenNumbersIterator(new int[] {1, -3, 2, 3, 5, 5, -4, 5, 6, 7});
        System.out.println(evenNumbersIterator.next());
        System.out.println(evenNumbersIterator.next());
        System.out.println(evenNumbersIterator.next());
    }
}