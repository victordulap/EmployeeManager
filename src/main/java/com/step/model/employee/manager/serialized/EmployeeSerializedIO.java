package com.step.model.employee.manager.serialized;

import com.step.model.employee.Employee;
import com.step.model.employee.manager.EmployeeFileIO;
import com.step.utilities.FileUtilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSerializedIO implements EmployeeFileIO {
    private String path = ".\\data\\";
    private String fileName = "employeesSerialized.txt";
    private FileUtilities fileUtilities = new FileUtilities();

    @Override
    public List<Employee> importEmps() {
        File file = fileUtilities.newFile(this.path, this.fileName);
        List<Employee> importedEmployees = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fis)) {

            while (true) {
                try {
                    Object o = in.readObject();
                    if (o == null) {
                        break;
                    }

                    importedEmployees.add((Employee) o);
                } catch (Exception e) {
                    // eof
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return importedEmployees;
    }

    @Override
    public void exportEmps(List<Employee> emps) {
        File file = fileUtilities.newFile(this.path, this.fileName);

        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {

            for (Employee emp : emps) {
                out.writeObject(emp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
