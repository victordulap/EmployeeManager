package com.step.model.employee;

import com.step.model.employee.manager.EmployeeManager;
import com.step.model.employee.manager.EmployeeManagerInMemory;

import java.util.List;

public class EmployeeFinder {
    //    todo: send employee manager type
    private EmployeeManager em = new EmployeeManagerInMemory();

    public Employee findById(List<Employee> employees, int id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
