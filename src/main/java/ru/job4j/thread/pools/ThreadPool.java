package ru.job4j.thread.pools;

import ru.job4j.thread.notifyall.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);

    public ThreadPool() throws InterruptedException {
        int size = Runtime.getRuntime().availableProcessors();
        work(() -> System.out.println(Thread.currentThread().getName()));
        work(() -> System.out.println(Thread.currentThread().getName()));
        for (int i = 0; i < size; i++) {
            threads.add((new Thread(tasks.poll())));
        }
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        threadPool.work(() -> System.out.println(Thread.currentThread().getName()));
        threadPool.work(() -> System.out.println(Thread.currentThread().getName()));
        for (Thread thread : threadPool.getThreads()) {
            thread.start();
        }
        threadPool.shutdown();
    }
}
