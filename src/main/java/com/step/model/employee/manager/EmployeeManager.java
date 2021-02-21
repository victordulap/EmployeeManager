package com.step.model.employee.manager;

import com.step.model.employee.Employee;

import java.util.List;

public interface EmployeeManager {
    void insert(Employee employee);
    boolean edit(int id, Employee newEmployee);
    boolean delete(int id);
    List<Employee> getEmployees();

//    void onLaunchApp(); or save();
    void save();

//    void onCloseApp(); or load();
    void load();
}
