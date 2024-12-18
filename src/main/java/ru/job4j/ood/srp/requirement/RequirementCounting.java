package ru.job4j.ood.srp.requirement;

import ru.job4j.ood.srp.currency.Converter;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeDtoCsv;

import java.util.Calendar;
import java.util.List;

public class RequirementCounting extends AbstractRequirement {

    private final CurrencyConverter currencyConverter;
    private final Converter convert;
    private final DateTimeParser<Calendar> parser;

    public RequirementCounting(
            String[] headers,
            CurrencyConverter currencyConverter,
            Converter convert,
            DateTimeParser<Calendar> parser) {
        super(headers);
        this.currencyConverter = currencyConverter;
        this.convert = convert;
        this.parser = parser;
    }

    @Override
    public List<EmployeeDtoCsv> preparedList(List<Employee> source) {
        return source.stream()
                .map(e -> new EmployeeDtoCsv(
                        e.getName(),
                        parser.parse(e.getHired()),
                        parser.parse(e.getFired()),
                        currencyConverter.convert(convert.getSource(), e.getSalary(), convert.getTarget())))
                .toList();
    }
}
