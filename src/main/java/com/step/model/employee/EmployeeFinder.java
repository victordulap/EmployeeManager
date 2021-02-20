package com.step.model.employee;

import com.step.model.employee.manager.EmployeeManager;
import com.step.model.employee.manager.memory.EmployeeManagerInMemory;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeFinder {
    //    todo: send employee manager type
    private EmployeeManager em = new EmployeeManagerInMemory();

    public Employee findById(List<Employee> employees, int id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public Employee findByIdnp(List<Employee> employees, String idnp) {
        return employees.stream()
                .filter(employee -> employee.getIdnp().equals(idnp))
                .findAny()
                .orElse(null);
    }

    public Employee findByNameAndSurname(List<Employee> employees, String name, String surname) throws Exception {
        List<Employee> empsByNameAndSurname = employees.stream()
                .filter(employee -> employee.getName().equals(name) && employee.getSurname().equals(surname))
                .collect(Collectors.toList());

        if(empsByNameAndSurname.size() > 1) {
            throw new Exception("Repetitive names");
        } else if(empsByNameAndSurname.size() <= 0) {
            return null;
        } else {
            return empsByNameAndSurname.get(0);
        }
    }
}
