package com.step.model.employee.search;

import com.step.model.employee.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeSort {
    public List<Employee> sortBySalary(List<Employee> emps, boolean asc) {
        return emps.stream()
                .sorted((o1, o2) -> {
                    if(asc) {
                        return o1.getSalary().compareTo(o2.getSalary());
                    } else {
                        return o2.getSalary().compareTo(o1.getSalary());
                    }
                })
                .collect(Collectors.toList());
    }

    public List<Employee> sortByBirthDate(List<Employee> emps, boolean asc) {
        return emps.stream()
                .sorted((o1, o2) -> {
                    if(asc) {
                        return o1.getBirthDate().compareTo(o2.getBirthDate());
                    } else {
                        return o2.getBirthDate().compareTo(o1.getBirthDate());
                    }
                })
                .collect(Collectors.toList());
    }

    public List<Employee> sortByEngagedOn(List<Employee> emps, boolean asc) {
        return emps.stream()
                .sorted((o1, o2) -> {
                    if(asc) {
                        return o1.getEngagedOn().compareTo(o2.getEngagedOn());
                    } else {
                        return o2.getEngagedOn().compareTo(o1.getEngagedOn());
                    }
                })
                .collect(Collectors.toList());
    }
}
