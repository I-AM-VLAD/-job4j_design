package ru.job4j.inout.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.get(key) == null) {
            throw new IllegalArgumentException("This key: 'Xms' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String str : args) {
                if (str.charAt(0) != '-') {
                    throw new IllegalArgumentException("Error: This argument 'request=?msg=Exit=' does not start with a '-' character");
                }
                if (str.charAt(1) == '=') {
                    throw new IllegalArgumentException("Error: This argument '-=?msg=Hello=' does not contain a key");
                }
                if (str.charAt(str.length() - 1) == '=') {
                    int count = 1;
                    for (int i = 0; i < str.length() - 1; i++) {
                        if (str.charAt(i) == '=') {
                            ++count;
                        }
                    }
                    if (count == 1) {
                        throw new IllegalArgumentException("Error: This argument '-encoding=' does not contain a value");
                    }
                }
                if (!str.contains("=")) {
                    throw new IllegalArgumentException("Error: This argument '-request?msgHello' does not contain an equal sign");
                }
                String[] array = str.split("-", 2)[1].split("=", 2);
                values.put(array[0], array[1]);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("encoding"));
    }
}
