package ru.job4j.thread;

public class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(final Node<T> next, final T value) {
        this.next = next;
        this.value = value;
    }
}
