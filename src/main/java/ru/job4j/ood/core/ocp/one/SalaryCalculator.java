package ru.job4j.ood.core.ocp.one;

public class SalaryCalculator {
    public double calculateSalary(Employee employee) {
        double salary = employee.getSalary();

        /**
         * Проблема - Если сотрудник работает в определенном отделе, ему начисляется бонус.
         * Решение - создать интерфейс подсчёта зарплаты.
         * Для каждого департамента написать свою реализацию это интерфейса.
         */

        if (employee.getDepartment().equals("IT")) {
            salary += 1000;
        } else if (employee.getDepartment().equals("Hr")) {
            salary += 100;
        }

        return salary;
    }
}
