package com.step.model.employee.manager.serialized;

import com.step.model.employee.Employee;
import com.step.model.employee.manager.EmployeeFileIO;

import java.util.List;

public class EmployeeSerializedIO implements EmployeeFileIO {
    private String path = ".\\data\\";
    private String fileName = "employeesSerialized.txt";

    @Override
    public List<Employee> importEmps() {
        return null;
    }

    @Override
    public void exportEmps(List<Employee> emps) {

    }
}
