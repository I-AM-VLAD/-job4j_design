package ru.job4j.thread.nonblockingalgoritm;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CASCountTest {
    @Test
    public void whenIncrement() throws InterruptedException {
        var casCount = new CASCount();
        Thread first = new Thread(
                () -> {
                    for (int i = 1; i <= 5; i++) {
                        casCount.increment();
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    for (int i = 1; i <= 5; i++) {
                        casCount.increment();
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(casCount.get()).isEqualTo(10);
    }
}