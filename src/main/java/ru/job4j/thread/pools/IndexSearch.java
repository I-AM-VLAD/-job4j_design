package ru.job4j.thread.pools;

import java.util.concurrent.RecursiveTask;

public class IndexSearch<T> extends RecursiveTask<Object> {
    private User userForIndex;
    private User[] users;
    private int startIndex;
    private int finishIndex;

    public IndexSearch(User userForIndex, User[] users, int startIndex, int finishIndex) {
        this.userForIndex = userForIndex;
        this.users = users;
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;
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
        int i = 0;
        int result = -1;
        int mid = (startIndex + finishIndex) / 2;
        User[] source = {new User("1", "111"), new User("2", "222")};
        IndexSearch leftSide = new IndexSearch(new User("1", "111"),
                source, 0, mid);
        IndexSearch rightSide = new IndexSearch(new User("1", "111"),
                source, mid + 1, source.length - 1);

        if (users[i].equals(userForIndex)) {
            result = i;
        }
        leftSide.fork();
        rightSide.fork();
        leftSide.join();
        rightSide.join();
        return result;
    }


}
