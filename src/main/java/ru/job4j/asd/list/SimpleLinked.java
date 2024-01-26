package ru.job4j.asd.list;

public interface SimpleLinked<E> extends Iterable<E> {
    void add(E value);
    E get(int index);
}
