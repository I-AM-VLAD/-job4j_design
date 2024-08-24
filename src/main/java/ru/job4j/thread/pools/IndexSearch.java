package ru.job4j.thread.pools;

import java.util.concurrent.RecursiveTask;

public class IndexSearch<T> extends RecursiveTask<Object> {
    private T forIndex;
    private T[] array;
    private int startIndex;
    private int finishIndex;
    private  int i = 0;

    public IndexSearch(T forIndex, T[] array, int startIndex, int finishIndex) {
        this.forIndex = forIndex;
        this.array = array;
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;
    }

    public int linearSearch() {
        int result = -1;
        for (int i = startIndex; i <= finishIndex; i++) {
            if (array[i].equals(forIndex)) {
                result = i;
            }
        }
        return result;
    }

    @Override
    protected Integer compute() {
        if (finishIndex - startIndex < 10) {
            return linearSearch();
        }
        int mid = (startIndex + finishIndex) / 2;
        IndexSearch leftSide = new IndexSearch(forIndex,
                array, startIndex, mid);
        IndexSearch rightSide = new IndexSearch(forIndex,
                array, mid + 1, finishIndex);
        leftSide.fork();
        rightSide.fork();
        leftSide.join();
        rightSide.join();
        return Math.max(startIndex, finishIndex);
    }


}