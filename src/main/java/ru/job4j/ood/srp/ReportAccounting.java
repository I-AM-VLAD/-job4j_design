package ru.job4j.ood.srp;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportAccounting implements Report {
    private final Store store;

    public ReportAccounting(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        CurrencyConverter currencyConverter = new InMemoryCurrencyConverter();
        StringBuilder text = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            double convertSalary = currencyConverter.convert(Currency.RUB, employee.getSalary(), Currency.EUR);
            text.append(convertSalary)
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
