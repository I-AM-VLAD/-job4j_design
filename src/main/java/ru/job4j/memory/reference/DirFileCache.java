package ru.job4j.memory.reference;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DirFileCache extends AbstractCache<String, String> {

    @Override
    protected String load(String key) {
        List<String> list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(key))) {
            read.lines().forEach(list::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list.toString();
    }
}
