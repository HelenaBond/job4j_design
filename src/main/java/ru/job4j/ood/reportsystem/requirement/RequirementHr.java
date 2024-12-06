package ru.job4j.ood.reportsystem.requirement;

import ru.job4j.ood.reportsystem.model.Employee;
import ru.job4j.ood.reportsystem.model.EmployeeDto;

import java.util.Comparator;
import java.util.List;

public class RequirementHr implements Requirement {

    @Override
    public List<EmployeeDto> preparedList(List<Employee> source) {
        return source.stream()
                .map(e -> new EmployeeDto(
                        e.getName(),
                        null,
                        null,
                        e.getSalary()))
                .sorted(Comparator.comparing(EmployeeDto::getSalary))
                .toList();
    }
}
