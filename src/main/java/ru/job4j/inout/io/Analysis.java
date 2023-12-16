package ru.job4j.inout.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public static void unavailable(String source, String target) {
        List<String> list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(list::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            String start = null;
            String finish = null;
            for (int i = 0; i < list.size(); i++) {
                String[] spl = list.get(i).split(" ");
                if ("400".equals(spl[0]) || "500".equals(spl[0])) {
                    start = spl[1];
                    while (i < list.size()) {
                       ++i;
                       if (i < list.size()) {
                           String[] splIn = list.get(i).split(" ");
                           if ("200".equals(splIn[0]) || "300".equals(splIn[0])) {
                               finish = splIn[1];
                               break;
                           }
                       }
                    }
                    out.println(start + ";" + finish);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
