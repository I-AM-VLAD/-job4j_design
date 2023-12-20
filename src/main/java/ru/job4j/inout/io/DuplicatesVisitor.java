package ru.job4j.inout.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private List<FileProperty> listAll = new ArrayList<>();

    public void printDuplicates() {
        System.out.println("Введите название файла: ");
        Scanner nameScanner = new Scanner(System.in);
        String name = nameScanner.nextLine();
        System.out.println("Введите размер файла: ");
        Scanner sizeScanner = new Scanner(System.in);
        int size = Integer.parseInt(sizeScanner.nextLine());
        for (FileProperty fileProperty : listAll) {
            if (fileProperty.getName().equals(name)
                    && fileProperty.getSize() == size) {
                System.out.println(fileProperty.getPath());
            }
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString(),
                file.toAbsolutePath().toString());
        listAll.add(fileProperty);
        return super.visitFile(file, attrs);
    }
}