package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.requirement.Requirement;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportCounting implements Report {

    private final Store store;
    private final Requirement requirement;
    private final DateTimeParser<Calendar> dateTimeParser;

    private static final String SEPARATOR = System.lineSeparator();

    public ReportCounting(Store store, Requirement requirement, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.requirement = requirement;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(SEPARATOR);
        List<Employee> readyList = requirement.preparedList(store.findBy(filter));
        for (Employee employee : readyList) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(employee.getSalary())
                    .append(SEPARATOR);
        }
        return text.toString();
    }
}
