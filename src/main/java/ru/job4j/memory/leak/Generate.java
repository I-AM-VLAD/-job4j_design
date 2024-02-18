package ru.job4j.memory.leak;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public interface Generate  {

    void generate();

    default List<String> read(String path) throws IOException {
        List<String> list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines().forEach(list::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
