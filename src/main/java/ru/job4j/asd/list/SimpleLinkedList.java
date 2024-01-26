package ru.job4j.asd.list;

import java.util.Iterator;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size = 0;
    private int modCount;
    private Node<E> head = null;
    private Node<E> current = head;

    @Override
    public void add(E value) {
        Node<E> node = new Node<E>(value, null);
        if (head == null) {
            head = node;
            ++size;
        } else {
            current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
            ++size;
        }
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
    public static void main(String[] args) {
        SimpleLinked<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
    }
}
