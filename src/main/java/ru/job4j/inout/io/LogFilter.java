package ru.job4j.inout.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> lines = new ArrayList<>();
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().forEach(lines::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String line : lines) {
            String[] strings = line.split(" ");
            for (String str : strings) {
                if (str.equals("404")) {
                    rsl.add(line);
                }
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);

    }
}
