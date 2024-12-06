package ru.job4j.ood.reportsystem.report;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.reportsystem.currency.Converter;
import ru.job4j.ood.reportsystem.currency.Currency;
import ru.job4j.ood.reportsystem.currency.CurrencyConverter;
import ru.job4j.ood.reportsystem.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.reportsystem.formatter.DateTimeParser;
import ru.job4j.ood.reportsystem.formatter.ReportDateTimeParser;
import ru.job4j.ood.reportsystem.model.Employee;
import ru.job4j.ood.reportsystem.reportformat.ReportFormatCsv;
import ru.job4j.ood.reportsystem.reportformat.ReportFormat;
import ru.job4j.ood.reportsystem.reportformat.ReportFormatJson;
import ru.job4j.ood.reportsystem.reportformat.ReportFormatXml;
import ru.job4j.ood.reportsystem.requirement.Requirement;
import ru.job4j.ood.reportsystem.requirement.RequirementCounting;
import ru.job4j.ood.reportsystem.requirement.RequirementEngine;
import ru.job4j.ood.reportsystem.requirement.RequirementHr;
import ru.job4j.ood.reportsystem.store.MemoryStore;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReportImplTest {

    private static MemoryStore store;
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
        List<Employee> employees = store.findBy(e -> !e.getName().equals("Ivan"));
        ReportFormat csv = new ReportFormatCsv(headers);
        Requirement requirementEngine = new RequirementEngine(parser);
        Report engine = new ReportImpl(requirementEngine, csv);
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
        assertThat(engine.generate(employees)).isEqualTo(expected.toString());
    }

    @Test
    public void whenTwoGeneratedToCsvFormatToHr() {
        String[] headers = new String[] {"name", "salary"};
        List<Employee> employees = store.findBy(e -> !e.getName().equals("Ivan"));
        ReportFormat csv = new ReportFormatCsv(headers);
       Requirement requirementHr = new RequirementHr();
        Report engine = new ReportImpl(requirementHr, csv);
        StringBuilder expected = new StringBuilder()
                .append("name; salary")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employees)).isEqualTo(expected.toString());
    }

    @Test
    public void whenOneGeneratedToCsvFormatToCounting() {
        String[] headers = new String[] {"name", "hired", "fired", "salary"};
        List<Employee> employees = store.findBy(e -> e.getName().equals("Ivan"));
        ReportFormat csv = new ReportFormatCsv(headers);
        Requirement requirementCounting = new RequirementCounting(currencyConverter, converter, parser);
        Report engine = new ReportImpl(requirementCounting, csv);
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
        assertThat(engine.generate(employees)).isEqualTo(expected.toString());
    }

    @Test
    public void whenOneGeneratedToXmlFormatToEngine() {
        List<Employee> employees = store.findBy(e -> e.getName().equals("Ivan"));
        ReportFormat xml = new ReportFormatXml();
        Requirement requirementEngine = new RequirementEngine(parser);
        Report engine = new ReportImpl(requirementEngine, xml);
        String expected = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>%s</name>
                        <hired>%s</hired>
                        <fired>%s</fired>
                        <salary>%s</salary>
                    </employee>
                </employees>
                """
                .formatted(
                        worker1.getName(),
                        parser.parse(worker1.getHired()),
                        parser.parse(worker1.getFired()),
                        worker1.getSalary());
        assertThat(engine.generate(employees)).isEqualTo(expected);
    }

    @Test
    public void whenTwoGeneratedToXmlFormatToHr() {
        List<Employee> employees = store.findBy(e -> !e.getName().equals("Ivan"));
        ReportFormat xml = new ReportFormatXml();
        Requirement requirementHr = new RequirementHr();
        Report engine = new ReportImpl(requirementHr, xml);
        String expected = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>%s</name>
                        <salary>%s</salary>
                    </employee>
                    <employee>
                        <name>%s</name>
                        <salary>%s</salary>
                    </employee>
                </employees>
                """
                .formatted(
                worker2.getName(),
                worker2.getSalary(),
                worker3.getName(),
                worker3.getSalary());
        assertThat(engine.generate(employees)).isEqualTo(expected);
    }

    @Test
    public void whenOneGeneratedToXmlFormatToCounting() {
        List<Employee> employees = store.findBy(e -> e.getName().equals("Ivan"));
        ReportFormat xml = new ReportFormatXml();
        Requirement requirementCounting = new RequirementCounting(currencyConverter, converter, parser);
        Report engine = new ReportImpl(requirementCounting, xml);
        String expected = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>%s</name>
                        <hired>%s</hired>
                        <fired>%s</fired>
                        <salary>%s</salary>
                    </employee>
                </employees>
                """
                .formatted(
                worker1.getName(),
                parser.parse(worker1.getHired()),
                parser.parse(worker1.getFired()),
                currencyConverter.convert(
                        converter.getSource(),
                        worker1.getSalary(),
                        converter.getTarget()));
        assertThat(engine.generate(employees)).isEqualTo(expected);
    }

    @Test
    public void whenOneGeneratedToJsonFormatToEngine() {
        List<Employee> employees = store.findBy(e -> e.getName().equals("Ivan"));
        ReportFormat json = new ReportFormatJson();
        Requirement requirementEngine = new RequirementEngine(parser);
        Report engine = new ReportImpl(requirementEngine, json);
        String expected = """
                [
                  {
                    "name": "%s",
                    "hired": "%s",
                    "fired": "%s",
                    "salary": %s
                  }
                ]"""
                .formatted(
                worker1.getName(),
                parser.parse(worker1.getHired()),
                parser.parse(worker1.getFired()),
                worker1.getSalary());
        assertThat(engine.generate(employees)).isEqualTo(expected);
    }

    @Test
    public void whenTwoGeneratedToJsonFormatToHr() {
        List<Employee> employees = store.findBy(e -> !e.getName().equals("Ivan"));
        ReportFormat json = new ReportFormatJson();
        Requirement requirementHr = new RequirementHr();
        Report engine = new ReportImpl(requirementHr, json);
        String expected = """
                [
                  {
                    "name": "%s",
                    "salary": %s
                  },
                  {
                    "name": "%s",
                    "salary": %s
                  }
                ]"""
                .formatted(
                        worker2.getName(),
                        worker2.getSalary(),
                        worker3.getName(),
                        worker3.getSalary());
        assertThat(engine.generate(employees)).isEqualTo(expected);
    }

    @Test
    public void whenOneGeneratedToJsonFormatToCounting() {
        List<Employee> employees = store.findBy(e -> e.getName().equals("Ivan"));
        ReportFormat json = new ReportFormatJson();
        Requirement requirementCounting = new RequirementCounting(currencyConverter, converter, parser);
        Report engine = new ReportImpl(requirementCounting, json);
        String expected = """
                [
                  {
                    "name": "%s",
                    "hired": "%s",
                    "fired": "%s",
                    "salary": %s
                  }
                ]"""
                .formatted(
                        worker1.getName(),
                        parser.parse(worker1.getHired()),
                        parser.parse(worker1.getFired()),
                        currencyConverter.convert(
                                converter.getSource(),
                                worker1.getSalary(),
                                converter.getTarget()
                        ));
        assertThat(engine.generate(employees)).isEqualTo(expected);
    }
}