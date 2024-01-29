package ru.job4j.asd.list;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int size = 0;
    public T poll() {
        while (size != 0) {
            output.push(input.pop());
            --size;
        }
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        ++size;
    }

    public static void main(String[] args) {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.poll();
        queue.push(3);
        queue.poll();
    }
}