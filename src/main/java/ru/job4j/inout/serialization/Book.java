package ru.job4j.inout.serialization;

public class Book {
    private String author;

    public Book(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{"
                + "author='" + author + '\''
                + '}';
    }
}

