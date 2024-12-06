package ru.job4j.ood.reportsystem.requirement;

import ru.job4j.ood.reportsystem.currency.Converter;
import ru.job4j.ood.reportsystem.currency.CurrencyConverter;
import ru.job4j.ood.reportsystem.formatter.DateTimeParser;
import ru.job4j.ood.reportsystem.model.Employee;
import ru.job4j.ood.reportsystem.model.EmployeeDto;

import java.util.Calendar;
import java.util.List;

public class RequirementCounting implements Requirement {

    private final CurrencyConverter currencyConverter;
    private final Converter convert;
    private final DateTimeParser<Calendar> dateTimeParser;

    public RequirementCounting(
            CurrencyConverter currencyConverter,
            Converter convert,
            DateTimeParser<Calendar> dateTimeParser) {
        this.currencyConverter = currencyConverter;
        this.convert = convert;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<EmployeeDto> preparedList(List<Employee> source) {
        return source.stream()
                .map(e -> new EmployeeDto(
                        e.getName(),
                        dateTimeParser.parse(e.getHired()),
                        dateTimeParser.parse(e.getFired()),
                        currencyConverter.convert(
                                convert.getSource(),
                                e.getSalary(),
                                convert.getTarget())))
                .toList();
    }
}
