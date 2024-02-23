package ru.job4j.memory.reference;

import java.io.File;
import java.util.Scanner;

public class Emulator {
    public static final String MENU = "Введите 1, чтобы ввести относительный путь кэшируемого файла.\n"
            + "                Введите 2, чтобы загрузить содержимое файла в кэш.\n"
            + "                Введите 3, чтобы получить содержимое файла из кэша.\n"
            + "                Введите любое другое число для выхода.";
    private static String key = null;

    public static void main(String[] args) {
        DirFileCache dirFileCache = new DirFileCache();
        Scanner scannerChoice = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scannerChoice.nextLine());
            if (userChoice == 1) {
                Scanner scanner = new Scanner(System.in);
                String keyDir = scanner.nextLine();
                File file = new File(keyDir);
                if (!file.exists()) {
                    throw new IllegalArgumentException();
                }
                key = keyDir;
            } else if (userChoice == 2) {
                dirFileCache.put(key, dirFileCache.load(key));
            } else if (userChoice == 3) {
                dirFileCache.get(key);
            } else {
                run = false;
                System.out.println("Конец работы");
            }
        }
    }
}
