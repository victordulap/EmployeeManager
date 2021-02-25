package com.step.model.employee.manager.csv;

import com.step.model.employee.Employee;
import com.step.model.employee.Job;
import com.step.model.employee.manager.EmployeeFileIO;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCsvIO implements EmployeeFileIO {
    private String path = ".\\data\\";
    private String fileName = "employeesCSV.txt";

    @Override
    public List<Employee> importEmps() {
        File file = new File(this.path + this.fileName);
        if (!file.exists()) {
            try {
                File pathToFile = new File(this.path);
                if(!pathToFile.exists()) {
                    pathToFile.mkdirs();
                }
                file.createNewFile();
                System.out.println("Created " + this.path + this.fileName);
            } catch (IOException e) {
                System.out.println("Undetected error on file creating process.");
            }
        }

        List<Employee> empsFromFile = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
                empsFromFile.add(this.convertFromCSVToEmployee(line));
            }
        } catch (IOException e) {
            System.out.println("Undetected error on file reading process.");
        }

        return empsFromFile;
    }

    @Override
    public void exportEmps(List<Employee> employees) {
        File file = new File(this.path + this.fileName);
        if (!file.exists()) {
            try {
                File pathToFile = new File(this.path);
                if(!pathToFile.exists()) {
                    pathToFile.mkdirs();
                }
                file.createNewFile();
                System.out.println("Created " + this.path + this.fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter writer = new FileWriter(file);

            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Employee employee : employees) {
                bufferedWriter.write(this.convertFromEmployeeToCSV(employee) + "\n");
            }
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private String convertFromEmployeeToCSV(Employee employee) {
        return employee.getId() + "," +
                employee.getName() + "," +
                employee.getSurname() + "," +
                employee.getIdnp() + "," +
                employee.getBirthDate() + "," +
                employee.getEngagedOn() + "," +
                employee.getSalary() + "," +
                employee.getJob();
    }

    private Employee convertFromCSVToEmployee(String csvLine) {
        String[] parameters = csvLine.split(",");

        Integer id = Integer.parseInt(parameters[0]);
        String name = parameters[1];
        String surname = parameters[2];
        String idnp = parameters[3];
        LocalDate birthDate = LocalDate.parse(parameters[4]);
        LocalDate engagedOn = LocalDate.parse(parameters[5]);
        Double salary = Double.parseDouble(parameters[6]);
        Job job = Job.valueOf(parameters[7]);

        return new Employee(id, name, surname, idnp, birthDate, engagedOn, salary, job);
    }
}
