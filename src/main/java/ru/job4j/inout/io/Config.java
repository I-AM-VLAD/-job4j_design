package ru.job4j.inout.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    @SuppressWarnings("checkstyle:SimplifyBooleanExpression")
    public void load() {
        List<String> list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(list::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String str : list) {
            if (str.length() == 0 || str.charAt(0) == '#') {
                continue;
            }
            if (str.charAt(0) == '=' || str.charAt(str.length() - 1) == '='
                || (str.charAt(0) == '=' && str.length() == 1)
                || !str.contains("=")) {
                throw new IllegalArgumentException();
            }
            String[] spl = str.split("=", 2);
            values.put(spl[0], spl[1]);
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}
