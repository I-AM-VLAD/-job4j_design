package ru.job4j.inout.serialization;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "book")
public class Book {
    @XmlAttribute
    private String author;

    public Book() {

    }

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

