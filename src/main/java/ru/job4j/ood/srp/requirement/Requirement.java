package ru.job4j.ood.srp.requirement;

import ru.job4j.ood.srp.model.Employee;

import java.util.List;

public interface Requirement {
    List<Employee> preparedList(List<Employee> source);
}
