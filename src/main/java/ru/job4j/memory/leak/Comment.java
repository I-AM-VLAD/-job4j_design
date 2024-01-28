package ru.job4j.memory.leak;

public class Comment {

    private String text;

    private User user;

    public Comment(String text, User user) {
        this.text = text;
        this.user = user;
    }

    /*getter/setter*/

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
