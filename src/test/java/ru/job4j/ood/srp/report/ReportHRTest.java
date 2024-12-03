package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.requirement.Requirement;
import ru.job4j.ood.srp.requirement.RequirementHR;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportHRTest {
    @Test
    public void whenOneGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Requirement requirement = new RequirementHR();
        Report hr = new ReportHR(store, requirement);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(hr.generate(employee -> true)).isEqualTo(expected.toString());
    }
}