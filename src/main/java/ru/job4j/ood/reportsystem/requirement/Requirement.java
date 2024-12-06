package ru.job4j.ood.reportsystem.requirement;

import ru.job4j.ood.reportsystem.model.Employee;
import ru.job4j.ood.reportsystem.model.EmployeeDto;

import java.util.List;

/**
 * Каждая реализация может менять состояние данных из входящего списка перед маппингом в EmployeeDto.
 */
public interface Requirement {
    List<EmployeeDto> preparedList(List<Employee> source);
}
