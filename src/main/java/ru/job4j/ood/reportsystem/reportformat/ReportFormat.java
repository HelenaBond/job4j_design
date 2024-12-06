package ru.job4j.ood.reportsystem.reportformat;

import ru.job4j.ood.reportsystem.model.EmployeeDto;

import java.util.List;

public interface ReportFormat {
    String toReportFormat(List<EmployeeDto> employees);
}
