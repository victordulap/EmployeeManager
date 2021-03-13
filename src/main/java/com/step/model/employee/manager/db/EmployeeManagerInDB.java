package com.step.model.employee.manager.db;

import com.step.model.employee.Employee;
import com.step.model.employee.EmployeeDataChecker;
import com.step.model.employee.manager.EmployeeManager;

import java.util.List;

public class EmployeeManagerInDB implements EmployeeManager {
    private EmployeeDAO empDao = new EmployeeDAO();
    private final EmployeeDataChecker edc = new EmployeeDataChecker();

    @Override
    public boolean insert(Employee employee) {
        boolean idnpIsUnique = edc.checkIfIDNPIsUnique(employee.getIdnp(), getEmployees());

        if (idnpIsUnique) {
            return empDao.insert(employee);
        } else {
            return false;
        }
    }

    @Override
    public boolean edit(int id, Employee newEmployee) {
        return empDao.update(id, newEmployee);
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
