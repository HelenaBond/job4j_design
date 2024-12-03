package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.requirement.Requirement;
import ru.job4j.ood.srp.store.Store;

import java.util.List;
import java.util.function.Predicate;

public class ReportHR implements Report {

    private final Store store;
    private final Requirement requirement;
    private static final String SEPARATOR = System.lineSeparator();

    public ReportHR(Store store, Requirement requirement) {
        this.store = store;
        this.requirement = requirement;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(SEPARATOR);
        List<Employee> readyList = requirement.preparedList(store.findBy(filter));
        for (Employee employee : readyList) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(SEPARATOR);
        }
        return text.toString();
    }
}
