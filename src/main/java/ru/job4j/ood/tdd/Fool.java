package ru.job4j.ood.tdd;

import java.util.Scanner;

public class Fool {

    public static void answerComputer(int startAt) {
        if (startAt % 3 == 0 && startAt % 5 == 0) {
            System.out.println("FizzBuzz");
        } else if (startAt % 3 == 0) {
            System.out.println("Fizz");
        } else if (startAt % 5 == 0) {
            System.out.println("Buzz");
        } else {
            System.out.println(startAt);
        }
    }

    public static int message() {
        System.out.println("Ошибка. Начинай снова.");
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            answerComputer(startAt);
            startAt++;
            var answer = input.nextLine();
            if (startAt % 3 == 0 && startAt % 5 == 0) {
                if (!"FizzBuzz".equals(answer)) {
                    startAt = message();
                }
            } else if (startAt % 3 == 0) {
                if (!"Fizz".equals(answer)) {
                    startAt = message();
                }
            } else if (startAt % 5 == 0) {
                if (!"Buzz".equals(answer)) {
                    startAt = message();
                }
            } else {
                if (!String.valueOf(startAt).equals(answer)) {
                    startAt = message();
                }
            }
            startAt++;
        }
    }
}
