package ru.job4j.inout.io;

import java.io.*;
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
            String[] str = line.split(" ");
                if ("404".equals(str[str.length - 2])) {
                    rsl.add(line);
                }

        }
        return rsl;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String elem : log) {
                out.println(elem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.save(logFilter.filter(), "data/404.txt");

    }
}
