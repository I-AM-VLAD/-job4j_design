package ru.job4j.ood.srp;

public interface CurrencyConverter {
    double convert(Currency source, double sourceValue, Currency target);
}
