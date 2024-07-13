package ru.job4j.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class Wgets implements Runnable {
    private final String url;
    private final int speed;
    private File target;

    public Wgets(String url, int speed, File target) {
        this.url = url;
        this.speed = speed;
        this.target = target;
    }

    @Override
    public void run() {
        var startAtOpen = System.currentTimeMillis();
        try (var input = new URL(url).openStream();
             var output = new FileOutputStream(target)) {
            System.out.println("Open connection: " + (System.currentTimeMillis() - startAtOpen) + " ms");
            var dataBuffer = new byte[512];
            int bytesRead;
            int countOfBytes = 0;
            var startAt = System.currentTimeMillis();
            while ((bytesRead = input.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                output.write(dataBuffer, 0, bytesRead);
                countOfBytes += bytesRead;
                if (countOfBytes >= speed) {
                   long timeDifference = System.currentTimeMillis() - startAt;
                   if (timeDifference < 1000) {
                       try {
                           Thread.sleep(countOfBytes / timeDifference);
                       } catch (InterruptedException e) {
                           Thread.currentThread().interrupt();
                       }
                   }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        var file = new File(args[2]);
        Thread wget = new Thread(new Wgets(url, speed, file));
        wget.start();
        wget.join();
    }
}
