package com.step.model.employee.manager.db;

import com.step.model.employee.Employee;
import com.step.model.employee.manager.EmployeeManager;

import java.util.List;

public class EmployeeManagerInDB implements EmployeeManager {
    private EmployeeDAO empDao = new EmployeeDAO();
    @Override
    public void insert(Employee employee) {
        empDao.insert(employee);
    }

    @Override
    public boolean edit(int id, Employee newEmployee) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return empDao.delete(id);
    }

    @Override
    public List<Employee> getEmployees() {
        return empDao.getAllEmps();
    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }
}
