package ru.job4j.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;

public class GetContent {
    private final File file;

    public GetContent(final File file) {
        this.file = file;
    }

    public String content(Predicate<Character> filter) throws IOException {
        InputStream input = new FileInputStream(file);
        String output = "";
        int data;
        while ((data = input.read()) > 0) {
            if (filter.test((char) data)) {
                output += (char) data;
            }
        }
        input.close();
        return output;
    }

    public String getContentWithoutUnicode() throws IOException {
        return content(x -> x < 0x80);
    }

    public String getContent() throws IOException {
        return content(x -> true);
    }
}
