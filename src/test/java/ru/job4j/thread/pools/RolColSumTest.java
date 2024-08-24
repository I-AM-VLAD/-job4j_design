package ru.job4j.thread.pools;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.*;

class RolColSumTest {
    @Test
    public void whenSequential() {
        int[][] matrix = {{1, 2, 3},
                          {4, 5, 6},
                          {7, 8, 9}};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            list.add(RolColSum.sum(matrix)[i].getRowSum());
            list.add(RolColSum.sum(matrix)[i].getColSum());
        }
        List<Integer> expected = Arrays.asList(6, 12, 15, 15, 24, 18);
        assertThat(list).isEqualTo(expected);
    }

    @Test
    public void whenAsync() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 3},
                          {4, 5, 6},
                          {7, 8, 9}};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            list.add(RolColSum.asyncSum(matrix)[i].getRowSum());
            list.add(RolColSum.asyncSum(matrix)[i].getColSum());
        }
        List<Integer> expected = Arrays.asList(6, 12, 15, 15, 24, 18);
        assertThat(list).isEqualTo(expected);
    }
}