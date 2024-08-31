package ru.job4j.thread.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {


    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = new Sums();
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sums[i].setRowSum(sums[i].getRowSum() + matrix[i][j]);
                sums[i].setColSum(sums[i].getColSum() + matrix[j][i]);
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
                        sums.setRowSum(sums.getRowSum() + matrix[i][j]);
                        sums.setColSum(sums.getColSum() + matrix[j][i]);
                    }
                    return sums;
                }
        );
    }

}
