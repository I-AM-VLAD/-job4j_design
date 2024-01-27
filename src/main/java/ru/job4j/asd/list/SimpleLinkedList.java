package ru.job4j.asd.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> head = null;
    private Node<E> current = head;

    @Override
    public void add(E value) {
        Node<E> node = new Node<E>(value, null);
        if (head == null) {
            head = node;
            ++size;
            ++modCount;
        } else {
            current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
            ++size;
            ++modCount;
        }
    }

    @Override
    public E get(int index) {
        E result = null;
        if (Objects.checkIndex(index, size) == index) {
            int localIndex = 0;
            current = head;
            while (localIndex != index) {
                current = current.next;
                ++localIndex;
            }
            result = current.item;
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            Node<E> currentIter = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return  currentIter != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> saveNode = currentIter;
                currentIter = currentIter.next;
                return saveNode.item;
            }
        };
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
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
    }
}
