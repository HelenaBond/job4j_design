package ru.job4j.ood.reportsystem.report;

import ru.job4j.ood.reportsystem.model.Employee;

import java.util.List;

public interface Report {
    String generate(List<Employee> employees);
}
