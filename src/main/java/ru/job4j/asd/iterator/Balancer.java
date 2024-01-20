package ru.job4j.asd.iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    private  List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    private int index = 0;

    public void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        if (!source.hasNext()) {
            return;
        }
        while (index < nodes.size()) {
            if (source.hasNext()) {
                list1.add(source.next());
            }
            ++index;
        }
        while (source.hasNext()) {
            list2.add(source.next());
        }
        Iterator<Integer> iterator1 = list1.iterator();
        Iterator<Integer> iterator2 = list2.iterator();
        for (ArrayList<Integer> element : nodes) {
            if (iterator1.hasNext()) {
                element.add(iterator1.next());
            }
            if (iterator2.hasNext()) {
                element.add(iterator2.next());
            }
        }
        while (iterator2.hasNext()) {
            nodes.get(0).add(iterator2.next());
        }

    }
    public static void main(String[] args) {
        List<ArrayList<Integer>> nodes = List.of(
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        Iterator<Integer> source = List.of(1, 2).iterator();
        Balancer balancer = new Balancer();
        balancer.split(nodes, source);
    }
}
