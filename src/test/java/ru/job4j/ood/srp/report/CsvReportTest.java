package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Converter;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.requirement.*;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CsvReportTest {

    private static Store store;
    private static DateTimeParser<Calendar> parser;
    private static CurrencyConverter currencyConverter;
    private static Converter converter;
    private static Employee worker1;
    private static Employee worker2;
    private static Employee worker3;
    @BeforeAll
    public static void init() {
        store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        worker1 = new Employee("Ivan", now, now, 100);
        worker2 = new Employee("Petr", now, now, 1_000);
        worker3 = new Employee("Ben", now, now, 10_000);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        parser = new ReportDateTimeParser();
        currencyConverter = new InMemoryCurrencyConverter();
        converter = new Converter(Currency.EUR, Currency.RUB);
    }

    @Test
    public void whenTwoGeneratedToCsvFormatToEngine() {
        String[] headers = new String[] {"name", "hired", "fired", "salary"};
        Requirement requirementEngine = new RequirementEngine(headers, parser);
        Report engine = new CsvReport(store, requirementEngine);
        StringBuilder expected = new StringBuilder()
                .append("name; hired; fired; salary")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(parser.parse(worker2.getHired())).append(";")
                .append(parser.parse(worker2.getFired())).append(";")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(parser.parse(worker3.getHired())).append(";")
                .append(parser.parse(worker3.getFired())).append(";")
                .append(worker3.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(e -> !e.getName().equals("Ivan"))).isEqualTo(expected.toString());
    }

    @Test
    public void whenTwoGeneratedToCsvFormatToHr() {
        String[] headers = new String[] {"name", "salary"};
        Requirement requirementHr = new RequirementHR(headers);
        Report engine = new CsvReport(store, requirementHr);
        StringBuilder expected = new StringBuilder()
                .append("name; salary")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(e -> !e.getName().equals("Ivan"))).isEqualTo(expected.toString());
    }

    @Test
    public void whenOneGeneratedToCsvFormatToCounting() {
        String[] headers = new String[] {"name", "hired", "fired", "salary"};
        Requirement requirementCounting = new RequirementCounting(headers, currencyConverter, converter, parser);
        Report engine = new CsvReport(store, requirementCounting);
        StringBuilder expected = new StringBuilder()
                .append("name; hired; fired; salary")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(parser.parse(worker1.getHired())).append(";")
                .append(parser.parse(worker1.getFired())).append(";")
                .append(currencyConverter.convert(
                        converter.getSource(),
                        worker1.getSalary(),
                        converter.getTarget()))
                .append(System.lineSeparator());
        assertThat(engine.generate(e -> e.getName().equals("Ivan"))).isEqualTo(expected.toString());
    }
}
