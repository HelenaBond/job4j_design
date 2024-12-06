package ru.job4j.ood.reportsystem.report;

import ru.job4j.ood.reportsystem.model.Employee;
import ru.job4j.ood.reportsystem.model.EmployeeDto;
import ru.job4j.ood.reportsystem.reportformat.ReportFormat;
import ru.job4j.ood.reportsystem.requirement.Requirement;

import java.util.List;

/**
 * Сервис гибкий в границах свойств EmployeeDto.
 */
public class ReportImpl implements Report {
    private final Requirement requirement;
    private final ReportFormat reportFormat;

    /**
     * @param reportFormat not null
     * @param requirement not null
     */
    public ReportImpl(Requirement requirement, ReportFormat reportFormat) {
        this.requirement = requirement;
        this.reportFormat = reportFormat;
    }

    @Override
    public String generate(List<Employee> employees) {
        List<EmployeeDto> dtos = requirement.preparedList(employees);
        return reportFormat.toReportFormat(dtos);
    }
}
