package com.step.model.employee.manager.memory;

import com.step.model.employee.Employee;
import com.step.model.employee.manager.EmployeeManager;
import com.step.model.employee.manager.EmployeeManagerWithList;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManagerInMemory implements EmployeeManager {
    private List<Employee> employees = new ArrayList<>();

    private EmployeeManagerWithList eml = new EmployeeManagerWithList();

    @Override
    public void insert(Employee employee) {
        eml.insert(employees, employee);
    }

    @Override
    public boolean edit(int id, Employee newEmployee) {
        return eml.edit(employees, id, newEmployee);
    }

    @Override
    public boolean delete(int id) {
        return eml.delete(employees, id);
    }

    @Override
    public List<Employee> getEmployees() {
        return eml.getEmployees(employees);
    }

    @Override
    public void save() {
        // nothing to load as we ar using the app mode with no storing of data :)
    }

    @Override
    public void load() {
        // nothing to load as we ar using the app mode with no storing of data :)
    }
}
