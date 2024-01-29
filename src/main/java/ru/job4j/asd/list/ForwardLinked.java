package ru.job4j.asd.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<E> implements Iterable<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> head = null;
    private Node<E> current = head;

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

    public int getSize() {
        return size;
    }

    public void addFirst(E value) {
        head = new Node<E>(value, head);
        ++size;
        ++modCount;
    }


    public E deleteFirst() {
        if (head != null) {
            Node<E> temp = head;
            head = head.next;
            --size;
            ++modCount;
            temp.next = null;
            return temp.item;
        }
        throw new NoSuchElementException("Queue is empty");
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
}
