package ru.job4j.asd.list;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
       return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public int getSize() {
        return linked.getSize();
    }

    public static void main(String[] args) {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);

    }
}
