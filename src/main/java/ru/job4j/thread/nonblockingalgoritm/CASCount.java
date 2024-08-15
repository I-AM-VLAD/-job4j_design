package ru.job4j.thread.nonblockingalgoritm;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        int a;
        int b;
        do {
             a = counter.get();
             b = a + 1;
        } while (!counter.compareAndSet(a, b));
    }

    public int get() {
        return counter.get();
    }
}
