package ru.job4j.inout.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        List<String> list = new ArrayList<>();
        List<String> listOut = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(argsName.get("path")))) {
            reader.lines().forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] splitFilter = argsName.get("filter").split(",");
        List<Integer> indexOfFilter = new ArrayList<>();
        int i = 0;
        for (String string : list) {
            int index = 0;
            var scanner = new Scanner(new ByteArrayInputStream(string.getBytes()))
                    .useDelimiter(argsName.get("delimiter"));
                while (scanner.hasNext()) {
                   String strScanner = scanner.next();
                    if (i == 0) {
                        for (String strFilter : splitFilter) {
                            if (strScanner.equals(strFilter)) {
                                indexOfFilter.add(index);
                                listOut.add(strScanner);
                                listOut.add(argsName.get("delimiter"));
                            }
                        }
                    } else {
                        for (int count = 0; count < indexOfFilter.size(); count++) {
                            if (index == indexOfFilter.get(count)) {
                                listOut.add(strScanner);
                                listOut.add(argsName.get("delimiter"));
                                if (count == indexOfFilter.size() - 1) {
                                    listOut.add(System.lineSeparator());
                                }
                            }
                        }
                    }
                    ++index;
                }
             if (i == 0) {
                 listOut.add(System.lineSeparator());
             }
            ++i;
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(argsName.get("out")))) {
            listOut.forEach(writer::print);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /* здесь добавьте валидацию принятых параметров*/
        if (args.length != 4) {
            throw new IllegalArgumentException("The number of parameters is not 4");
        }
        ArgsName argsName = ArgsName.of(args);
        Path fileSource = Paths.get(argsName.get("path"));
        if (!Files.exists(fileSource)) {
            throw new IllegalArgumentException("sorce-файла не существует");
        }
        if (";".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException("delimiter is not ';'");
        }
        Path fileTarget = Paths.get(argsName.get("out"));
        if (!Files.exists(fileTarget)) {
            throw new IllegalArgumentException("target-файла не существует");
        }
        try {
            handle(argsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
