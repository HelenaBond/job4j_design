package ru.job4j.ood.reportsystem.model;

import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name", "hired", "fired", "salary"})
public class EmployeeDto {
    private String name;
    private String hired;
    private String fired;
    private Double salary;

    public EmployeeDto() {
    }

    public EmployeeDto(String name, String hired, String fired, Double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHired() {
        return hired;
    }

    public void setHired(String hired) {
        this.hired = hired;
    }

    public String getFired() {
        return fired;
    }

    public void setFired(String fired) {
        this.fired = fired;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
