package ru.job4j.ood.ocp.core.one;

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
        }

        return salary;
    }
}
