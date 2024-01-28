package ru.job4j.asd.list;

import java.util.NoSuchElementException;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();
    private int top;

    public T pop() {
       return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public static void main(String[] args) {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);

    }
}
