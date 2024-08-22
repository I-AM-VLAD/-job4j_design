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

    public T[] getArray() {
        return array;
    }

    public int linearSearch(User[] users, User userForIndex) {
        int result = -1;
        for (int i = 0; i < users.length; i++) {
            if (users[i].equals(userForIndex)) {
                result = i;
            }
        }
        return result;
    }

    @Override
    protected Integer compute() {
        int result = -1;
        int mid = (startIndex + finishIndex) / 2;
        IndexSearch leftSide = new IndexSearch(forIndex,
                array, startIndex, mid);
        IndexSearch rightSide = new IndexSearch(forIndex,
                array, mid + 1, finishIndex);
        if (leftSide.getArray().equals(forIndex) || rightSide.getArray().equals(forIndex)) {
            result = i;
        }
        i++;
        leftSide.fork();
        rightSide.fork();
        leftSide.join();
        rightSide.join();
        return result;
    }


}
