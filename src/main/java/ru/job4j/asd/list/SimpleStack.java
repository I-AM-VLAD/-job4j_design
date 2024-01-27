package ru.job4j.asd.list;

import java.util.NoSuchElementException;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();
    private int top;

    public T pop() {
        if (top >= 0) {
            return linked.get(top--);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void push(T value) {
        linked.add(value);
        top = linked.getSize() - 1;
    }

    public static void main(String[] args) {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);

    }
}
