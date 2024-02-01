package ru.job4j.asd.list;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemove() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, -2, 3, -4, -5));
        ListUtils.removeIf(input, i -> i < 0);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenReplace() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, -2, 0, -4, -5));
        ListUtils.replaceIf(input, i -> i == 0, 10);
        assertThat(input).hasSize(5).containsSequence(1, -2, 10, -4, -5);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, -2, 3, -4, 0));
        ListUtils.removeAll(input, Arrays.asList(1, -2, -4));
        assertThat(input).hasSize(2).containsSequence(3, 0);
    }

}
