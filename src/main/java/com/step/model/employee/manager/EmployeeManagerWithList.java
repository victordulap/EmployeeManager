package com.step.model.employee.manager;

import com.step.model.employee.Employee;
import com.step.model.employee.EmployeeDataChecker;

import java.util.List;

public class EmployeeManagerWithList {
    private final EmployeeDataChecker edc = new EmployeeDataChecker();

    public boolean insert(List<Employee> employees, Employee employee) {
        boolean idnpIsUnique = edc.checkIfIDNPIsUnique(employee.getIdnp(), employees);
        if (idnpIsUnique) {
            return employees.add(employee);
        } else {
            return false;
        }
    }

    public boolean edit(List<Employee> employees, int id, Employee newEmployee) {
        int employeeIndex = this.findEmployeeIndexById(employees, id);

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

    public boolean delete(List<Employee> employees, int id) {
        int employeeIndex = this.findEmployeeIndexById(employees, id);

        if (employeeIndex >= 0) {
            employees.remove(employeeIndex);
            return true;
        } else {
            return false;
        }
    }

    public List<Employee> getEmployees(List<Employee> employees) {
        return employees;
    }

    /**
     * @param id
     * @return the index of specified Employee by id, or -1 if not found
     */
    private int findEmployeeIndexById(List<Employee> employees, int id) {
        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElse(null);
        return employees.indexOf(employee);
    }
}
