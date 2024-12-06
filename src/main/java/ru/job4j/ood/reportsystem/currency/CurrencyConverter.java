package ru.job4j.ood.reportsystem.currency;

public interface CurrencyConverter {
    double convert(Currency source, double sourceValue, Currency target);
}
