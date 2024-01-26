package ru.job4j.asd.list;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size = 0;
    private int point = 0;
    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length != 0) {
            if (size < container.length) {
                container[size++] = value;
                ++modCount;
            }
            if (size == container.length) {
                container = Arrays.copyOf(container, container.length * 2);
            }
        } else {
            T[] one = (T[]) new Object[1];
            container = one;
            container[size++] = value;
            ++modCount;
        }
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = null;
        if (Objects.checkIndex(index, size) == index) {
            oldValue = container[index];
            container[index] = newValue;
        }
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T oldValue = null;
        if (Objects.checkIndex(index, size) == index) {
            oldValue = container[index];
            System.arraycopy(
                    container,
                    index + 1,
                    container,
                    index,
                    size - index - 1
            );
            container[size - 1] = null;
            --size;
            ++modCount;
        }
        return oldValue;
    }

    @Override
    public T get(int index) {
        if (Objects.checkIndex(index, size) == index) {
            return container[index];
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
               return container[point++];
            }
        };
    }

    public static void main(String[] args) {
        SimpleList<Integer> list = new SimpleArrayList<>(0);
        list.add(1);
        System.out.println(list.size());
    }
}
