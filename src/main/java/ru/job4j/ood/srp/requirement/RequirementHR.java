package ru.job4j.ood.srp.requirement;

import ru.job4j.ood.srp.model.Employee;

import java.util.Comparator;
import java.util.List;

public class RequirementHR implements Requirement {
    @Override
    public List<Employee> preparedList(List<Employee> source) {
        source.sort(Comparator.comparing(Employee::getSalary).reversed());
        return source;
    }
}
