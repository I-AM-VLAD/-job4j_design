package ru.job4j.thread.notifyall;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimpleBlockingQueueTest {
    @Test
    public void whenTestProducerAndConsumer() throws InterruptedException {
        var simpleBlockingQueue = new SimpleBlockingQueue<Integer>();
        int[] result = new int[1];
        var producer = new Thread(() -> {
            simpleBlockingQueue.offer(1);
        });
        var consumer = new Thread(() -> {
            result[0] = simpleBlockingQueue.poll();
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(result[0]).isEqualTo(1);
    }
}