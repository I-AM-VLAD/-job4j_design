package ru.job4j.ood.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportHRTest {
    @Test
    public void whenTestGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan1", now, now, 100);
        Employee worker2 = new Employee("Ivan2", now, now, 50);
        Employee worker3 = new Employee("Ivan3", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        ReportHR reportHR = new ReportHR(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append("Ivan3 200.0")
                .append(System.lineSeparator())
                .append("Ivan1 100.0")
                .append(System.lineSeparator())
                .append("Ivan2 50.0")
                .append(System.lineSeparator());

        assertThat(reportHR.generate(employee -> true)).isEqualTo(expected.toString());
    }
}