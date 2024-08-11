package ru.job4j.thread.nonblockingalgoritm;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CASCountTest {
    @Test
    public void whenIncrementOnce() {
        var casCount = new CASCount();
        casCount.increment();
        assertThat(casCount.get()).isEqualTo(1);
    }

    @Test
    public void whenWithoutIncrement() {
        var casCount = new CASCount();
        assertThat(casCount.get()).isEqualTo(0);
    }

    @Test
    public void whenIncrementTwice() {
        var casCount = new CASCount();
        casCount.increment();
        casCount.increment();
        assertThat(casCount.get()).isEqualTo(2);
    }
}