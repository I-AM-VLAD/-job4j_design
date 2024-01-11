package ru.job4j.inout.serialization;

import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Library {
    private boolean webSite;
    private int visitors;
    private Book book;
    private String director;
    private String[] employees;

    public Library(boolean webSite, int visitors, Book book, String director, String... employees) {
        this.webSite = webSite;
        this.visitors = visitors;
        this.book = book;
        this.director = director;
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Library{"
                + "webSite=" + webSite
                + ", visitors=" + visitors
                + ", book=" + book
                + ", director=" + director
                + ", employees=" + Arrays.toString(employees)
                + '}';
    }

    public static void main(String[] args) {
        Library library = new Library(false, 150, new Book("Pushkin"), "Ivanov",
                new String[] {"Кузнецов", "Петров"});
        Gson gson = new GsonBuilder().create();
        String strJson = gson.toJson(library);
        System.out.println(strJson);
        Library libFromJson = gson.fromJson(strJson, Library.class);
        System.out.println(libFromJson);

    }
}
