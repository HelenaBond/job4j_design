package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Converter;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.requirement.Requirement;
import ru.job4j.ood.srp.requirement.RequirementCounting;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportCountingTest {
    @Test
    public void whenOneGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        CurrencyConverter currencyConverter = new InMemoryCurrencyConverter();
        Converter convert = new Converter(Currency.EUR, Currency.RUB);
        Requirement requirement = new RequirementCounting(currencyConverter, convert);
        Report counting = new ReportCounting(store, requirement, parser);
        double expectedSalary = currencyConverter.convert(Currency.EUR, 100, Currency.RUB);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(expectedSalary)
                .append(System.lineSeparator());
        assertThat(counting.generate(employee -> true)).isEqualTo(expected.toString());
    }
}