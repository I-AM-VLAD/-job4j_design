package ru.job4j.ood.srp;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    void add(Employee employee);

    List<Employee> findBy(Predicate<Employee> filter);
}
