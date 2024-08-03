package ru.job4j.thread.notifyall;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private int size = 100;
    private final Object monitor = this;

    public synchronized void offer(T value) throws InterruptedException {
            while (queue.size() > size) {
                monitor.wait();
            }
            monitor.notify();
            queue.offer(value);
    }

    public synchronized T poll() throws InterruptedException {
            while (queue.size() == 0) {
                monitor.wait();
            }
            monitor.notify();
            return queue.poll();
    }
}
