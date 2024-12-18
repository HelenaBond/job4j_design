package ru.job4j.ood.srp.requirement;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeDtoCsv;

import java.util.Calendar;
import java.util.List;

public class RequirementEngine extends AbstractRequirement {
    private final DateTimeParser<Calendar> parser;

    public RequirementEngine(String[] headers, DateTimeParser<Calendar> parser) {
        super(headers);
        this.parser = parser;
    }

    @Override
    public List<EmployeeDtoCsv> preparedList(List<Employee> source) {
        return source.stream()
                .map(e -> new EmployeeDtoCsv(
                        e.getName(),
                        parser.parse(e.getHired()),
                        parser.parse(e.getFired()),
                        e.getSalary()))
                .toList();
    }
}
