package ru.job4j.ood.srp.requirement;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeDtoCsv;

import java.util.List;

public interface Requirement {
    List<EmployeeDtoCsv> preparedList(List<Employee> source);
    String[] getHeaders();
}
