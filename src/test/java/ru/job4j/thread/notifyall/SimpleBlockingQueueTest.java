package ru.job4j.thread.notifyall;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimpleBlockingQueueTest {
    @Test
    public void whenTestProducerAndConsumer() throws InterruptedException {
        var simpleBlockingQueue = new SimpleBlockingQueue<Integer>(100);
        int[] result = new int[1];
        var producer = new Thread(() -> {
            try {
                simpleBlockingQueue.offer(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        var consumer = new Thread(() -> {
            try {
                result[0] = simpleBlockingQueue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(result[0]).isEqualTo(1);
    }
}