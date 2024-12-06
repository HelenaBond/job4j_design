package ru.job4j.ood.reportsystem.requirement;

import ru.job4j.ood.reportsystem.formatter.DateTimeParser;
import ru.job4j.ood.reportsystem.model.Employee;
import ru.job4j.ood.reportsystem.model.EmployeeDto;

import java.util.Calendar;
import java.util.List;

public class RequirementEngine implements Requirement {
    private final DateTimeParser<Calendar> dateTimeParser;

    public RequirementEngine(DateTimeParser<Calendar> dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<EmployeeDto> preparedList(List<Employee> source) {
        return source.stream()
                .map(e -> new EmployeeDto(
                        e.getName(),
                        dateTimeParser.parse(e.getFired()),
                        dateTimeParser.parse(e.getHired()),
                        e.getSalary()))
                .toList();
    }
}
