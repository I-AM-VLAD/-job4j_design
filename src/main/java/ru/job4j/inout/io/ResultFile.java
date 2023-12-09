package ru.job4j.inout.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/matrix.txt")) {
            int size = 9;
            int[][] table = Matrix.multiple(9);
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < size; i++) {
                for (int j  = 0; j < size; j++) {
                    text.append(table[i][j]);
                    text.append("  ");
                }
                text.append(System.lineSeparator());
            }
            out.write(text.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

