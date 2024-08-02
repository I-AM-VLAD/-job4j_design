package ru.job4j.thread.notifyall;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    private final Object monitor = this;

    public void offer(T value) {
        synchronized (monitor) {
            boolean result = queue.offer(value);
            while (!result) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            monitor.notify();
        }
    }

    public T poll() {
        synchronized (monitor) {
            T result = queue.poll();
            while (result == null) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            monitor.notify();
            return result;
        }
    }
}
