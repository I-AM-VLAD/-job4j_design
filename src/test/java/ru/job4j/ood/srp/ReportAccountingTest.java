package ru.job4j.ood.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportAccountingTest {
    @Test
    public void whenTestGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan1", now, now, 100);
        store.add(worker1);
        ReportAccounting reportAccounting = new ReportAccounting(store);
        assertThat(reportAccounting.generate(employee -> true)).isEqualTo("1.66" + System.lineSeparator());
    }
}