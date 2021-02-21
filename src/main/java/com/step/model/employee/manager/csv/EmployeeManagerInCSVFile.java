package com.step.model.employee.manager.csv;

import com.step.model.employee.Employee;
import com.step.model.employee.manager.EmployeeManager;
import com.step.model.employee.manager.EmployeeManagerWithList;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManagerInCSVFile implements EmployeeManager {
    private List<Employee> employees = new ArrayList<>();
    private EmployeeManagerWithList eml = new EmployeeManagerWithList();
    private EmployeeCsvIO csvIO = new EmployeeCsvIO();

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
        // call function to save data in file
        csvIO.exportToCSVFile(employees);
    }

    @Override
    public void load() {
        // call function to get data from the file

    }
}
