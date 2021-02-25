package com.step.model.employee.manager;

import com.step.model.employee.Employee;

import java.io.File;
import java.util.List;

public interface EmployeeFileIO {
    List<Employee> importEmps();
    void exportEmps(List<Employee> emps);
}
