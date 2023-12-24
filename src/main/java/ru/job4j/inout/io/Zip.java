package ru.job4j.inout.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                    zip.closeEntry();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validate(String directory, String exclude, String output) {
        if (directory == null || exclude == null || output == null) {
            throw new IllegalArgumentException("The number of parameters is not 3");
        }
        Path dir = Paths.get(directory);
        if (!Files.exists(dir)) {
            throw new IllegalArgumentException("Директория не существует");
        }
        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException("Это не директория");
        }
        if (exclude.charAt(0) != '.' || exclude.length() == 1) {
            throw new IllegalArgumentException("Расширение начинается не из '.' или кроме '.' нету символов");
        }
        if (!output.contains(".zip")) {
            throw new IllegalArgumentException("Расширение итогового файла не '.zip'");
        }
        return true;
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        Zip zip = new Zip();
        if (zip.validate(argsName.get("d"), argsName.get("e"), argsName.get("o"))) {
            Search searchExcept = new Search();
            Path start = Paths.get(argsName.get("d"));
            try {
                List<Path> listSource = searchExcept.search(start, p -> !p.toFile().getName().endsWith(argsName.get("e")));
                zip.packFiles(listSource, new File(argsName.get("o")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
