package ru.job4j.inout.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (validate(args)) {
            Path start = Paths.get(args[0]);
            search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }
    }

    public static boolean validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("The number of parameters is not 2");
        }
        Path dir = Paths.get(args[0]);
        if (!Files.exists(dir)) {
            throw new IllegalArgumentException("Директория не существует");
        }
        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException("Это не директория");
        }
        if (args[1].charAt(0) != '.' || args[1].length() == 1) {
            throw new IllegalArgumentException("Расширение начинается не из '.' или кроме '.' нету символов");
        }
        return true;
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}