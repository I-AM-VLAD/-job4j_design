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
        Sums[] sums = RolColSum.sum(matrix);
        Sums sum1 = new Sums();
        sum1.setRowSum(6);
        sum1.setColSum(12);
        Sums sum2 = new Sums();
        sum2.setRowSum(15);
        sum2.setColSum(15);
        Sums sum3 = new Sums();
        sum3.setRowSum(24);
        sum3.setColSum(18);
        Sums[] expected = {sum1, sum2, sum3};
        assertThat(sums).isEqualTo(expected);
    }

    @Test
    public void whenAsync() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 3},
                          {4, 5, 6},
                          {7, 8, 9}};
        Sums[] sums = RolColSum.asyncSum(matrix);
        Sums sum1 = new Sums();
        sum1.setRowSum(6);
        sum1.setColSum(12);
        Sums sum2 = new Sums();
        sum2.setRowSum(15);
        sum2.setColSum(15);
        Sums sum3 = new Sums();
        sum3.setRowSum(24);
        sum3.setColSum(18);
        Sums[] expected = {sum1, sum2, sum3};
        assertThat(sums).isEqualTo(expected);
    }
}