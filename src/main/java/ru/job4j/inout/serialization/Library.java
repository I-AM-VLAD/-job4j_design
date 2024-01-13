package ru.job4j.inout.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public String getDirector() {
        return director;
    }

    public int getVisitors() {
        return visitors;
    }

    public boolean isWebSite() {
        return webSite;
    }

    public static void main(String[] args) {
        /* JSONObject из json-строки */
        JSONObject jsonBook = new JSONObject("{\"author\":\"Pushkin\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Vlad");
        list.add("Anna");
        JSONArray jsonEmployees = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Library library = new Library(true, 35, new Book("Pushkin"), "Vlad", "Anna", "Pavel");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("webSite", library.isWebSite());
        jsonObject.put("visitors", library.getVisitors());
        jsonObject.put("director", library.getDirector());
        jsonObject.put("book", jsonBook);
        jsonObject.put("employees", jsonEmployees);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект library в json-строку */
        System.out.println(new JSONObject(library).toString());
    }
}
