package com.step.model.employee.manager.xml;

import com.step.model.employee.Employee;
import com.step.model.employee.manager.EmployeeLastIdIO;
import com.step.model.employee.manager.EmployeeManager;
import com.step.model.employee.manager.EmployeeManagerWithList;
import com.step.model.employee.manager.serialized.EmployeeSerializedIO;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManagerInXMLFile implements EmployeeManager {
    private List<Employee> employees = new ArrayList<>();
    private EmployeeManagerWithList eml = new EmployeeManagerWithList();
    private EmployeeXMLIO xmlIO = new EmployeeXMLIO();
    private EmployeeLastIdIO lastIdIO = new EmployeeLastIdIO("employeeXMLLastId.txt");

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
        // save emps
        xmlIO.exportEmps(employees);
        // save last id
        lastIdIO.save(Employee.getLastId());
    }

    @Override
    public void load() {
        // load emps
        this.employees.addAll(xmlIO.importEmps());
        // load last id
        Employee.setLastId(lastIdIO.load());
    }
}
