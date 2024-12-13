package ru.job4j.ood.srp.requirement;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeDtoCsv;

import java.util.Comparator;
import java.util.List;

public class RequirementHR extends AbstractRequirement {

    public RequirementHR(String[] headers) {
        super(headers);
    }

    @Override
    public List<EmployeeDtoCsv> preparedList(List<Employee> source) {
        return source.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .map(e -> new EmployeeDtoCsv(
                        e.getName(),
                        null,
                        null,
                        e.getSalary()))
                .toList();
    }
}
