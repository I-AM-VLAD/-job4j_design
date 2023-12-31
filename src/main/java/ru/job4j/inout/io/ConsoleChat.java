package ru.job4j.inout.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> answer = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            reader.lines().forEach(answer::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public void run() {
        List<String> answer = readPhrases();
        List<String> log = new ArrayList<>();
        boolean flagStop = false;
        int a = 10;
        while (a-- != 0) {
            Scanner scanner = new Scanner(System.in);
            String question = scanner.nextLine();
            int random = new Random().nextInt(answer.size());
            log.add(question);
            if (question.contains(OUT)) {
                break;
            }
            if (question.contains(STOP)) {
                flagStop = true;
            }
            if (question.contains(CONTINUE)) {
                flagStop = false;
            }

            if (!flagStop) {
                System.out.println(answer.get(random));
                log.add(answer.get(random));
            }
        }
        saveLog(log);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/chat.log", "data/botAnswers.txt");
        cc.run();
    }
}

