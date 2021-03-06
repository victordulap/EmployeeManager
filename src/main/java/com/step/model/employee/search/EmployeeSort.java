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
}
