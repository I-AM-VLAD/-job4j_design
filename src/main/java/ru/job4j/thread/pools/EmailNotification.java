package ru.job4j.thread.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

   public void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                send(subject(user), body(user), user.getEmail());
            }
        });
   }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {

    }

    private String body(User user) {
       return new StringBuilder()
               .append("Event for ")
               .append(user.getUsername())
               .toString();
    }

    private String subject(User user) {
       return new StringBuilder()
               .append("Notification ")
               .append(user.getUsername())
               .append(", ")
               .append(" email ")
               .append(user.getEmail())
               .toString();
    }
}
