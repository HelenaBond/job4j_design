package ru.job4j.ood.srp.requirement;

import ru.job4j.ood.srp.currency.Converter;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.model.Employee;

import java.util.List;

public class RequirementCounting implements Requirement {

    private final CurrencyConverter currencyConverter;

    private final Converter convert;

    public RequirementCounting(CurrencyConverter currencyConverter, Converter convert) {
        this.currencyConverter = currencyConverter;
        this.convert = convert;
    }

    @Override
    public List<Employee> preparedList(List<Employee> source) {
        source.forEach(e -> e.setSalary(
                currencyConverter.convert(
                        convert.getSource(),
                        e.getSalary(),
                        convert.getTarget())
        ));
        return source;
    }
}
