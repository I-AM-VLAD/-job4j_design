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

    public String content(Predicate<Character> filter) {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream input = new FileInputStream(file)) {
            int data;
            while ((data = input.read()) != 0) {
                if (filter.test((char) data)) {
                    stringBuilder.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public String getContentWithoutUnicode() {
        return content(x -> x < 0x80);
    }

    public String getContent() {
        return content(x -> true);
    }
}
