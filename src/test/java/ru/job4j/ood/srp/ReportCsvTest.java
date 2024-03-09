package ru.job4j.ood.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportCsvTest {
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
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        var date = parser.parse(worker1.getHired());
        ReportCsv reportCsv = new ReportCsv(store, parser);
        StringBuilder expected = new StringBuilder()
                .append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator())
                .append("Ivan1;").append(date).append(";").append(date).append(";").append("100.0;")
                .append(System.lineSeparator())
                .append("Ivan2;").append(date).append(";").append(date).append(";").append("50.0;")
                .append(System.lineSeparator())
                .append("Ivan3;").append(date).append(";").append(date).append(";").append("200.0;")
                .append(System.lineSeparator());
        assertThat(reportCsv.generate(employee -> true)).isEqualTo(expected.toString());

    }
}