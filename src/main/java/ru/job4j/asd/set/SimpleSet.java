package ru.job4j.asd.set;

public interface SimpleSet<T> extends Iterable<T> {
    boolean add(T value);
    boolean contains(T value);
}
