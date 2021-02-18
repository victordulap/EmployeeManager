package com.step.model.employee.manager;

import com.step.model.employee.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManagerInMemory implements EmployeeManager {
    private List<Employee> employees = new ArrayList<>();

    @Override
    public void insert(Employee employee) {
        employees.add(employee);
    }

    @Override
    public boolean edit(int id, Employee newEmployee) {
        int employeeIndex = this.findEmployeeIndexById(id);

        if (employeeIndex >= 0) {
            employees.get(employeeIndex).setName(newEmployee.getName());
            employees.get(employeeIndex).setSurname(newEmployee.getSurname());
            employees.get(employeeIndex).setSalary(newEmployee.getSalary());
            employees.get(employeeIndex).setJob(newEmployee.getJob());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        int employeeIndex = this.findEmployeeIndexById(id);

        if (employeeIndex >= 0) {
            employees.remove(employeeIndex);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Employee> getEmployees() {
        return this.employees;
    }

    /**
     * @param id
     * @return the index of specified Employee by id, or -1 if not found
     */
    private int findEmployeeIndexById(int id) {
        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElse(null);
        return employees.indexOf(employee);
    }
}
