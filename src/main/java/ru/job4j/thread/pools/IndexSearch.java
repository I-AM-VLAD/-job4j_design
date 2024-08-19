package ru.job4j.thread.pools;

import java.util.concurrent.RecursiveTask;

public class IndexSearch extends RecursiveTask<Integer> {
    private User userForIndex;
    private User[] users;

    public IndexSearch(User userForIndex, User[] users) {
        this.userForIndex = userForIndex;
        this.users = users;
    }

    public int search(User[] users, User userForIndex) {
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
        User[] source = {new User("1", "111"), new User("2", "222")};
        IndexSearch indexSearch = new IndexSearch(new User("1", "111"), source);
        if (users[i].equals(userForIndex)) {
            result = i;
        }
        i++;
        if (i >= users.length) {
            return result;
        }
        indexSearch.fork();
        indexSearch.join();
        return result;
    }


}
