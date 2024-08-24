package ru.job4j.thread.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum = 0;
        private int colSum = 0;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = new Sums();
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sums[i].rowSum += matrix[i][j];
                sums[i].colSum += matrix[j][i];
            }
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = new Sums();
        }
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = getTask(i, matrix).get();
        }
        return sums;
    }

    public static CompletableFuture<Sums> getTask(int i, int[][] matrix) {
        return CompletableFuture.supplyAsync(
                () -> {
                    Sums sums = new Sums();
                    for (int j = 0; j < matrix.length; j++) {
                        sums.rowSum += matrix[i][j];
                        sums.colSum += matrix[j][i];
                    }
                    return sums;
                }
        );
    }

}
