package com.step.model.employee.manager.csv;

import com.step.model.employee.Employee;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EmployeeCsvIO {
    private String path = ".\\data\\";
    private String fileName = "employees.txt";
//    private String path = "./data/employees.txt";

//    protected static void importFromCSVFile() {
//        String path = ".\\data\\employees.txt"; // works
//        File file = new File(path);
//        if (!file.exists()) {
//            try {
//                boolean fileCreatedSuccessfully = file.createNewFile();
//                System.out.println("There was no file before, so a new file was created.");
//                Utilities.enterAnyValueToContinue();
//                return;
//            } catch (IOException e) {
//                System.out.println("Undetected error on file creating process.");
//            }
//        }
//
//        EmployeeManager.employees.clear();
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
////                System.out.println(line);
//                EmployeeManager.employees.add(convertFromCSVToEmployee(line));
//            }
//        } catch (IOException e) {
//            System.out.println("Undetected error on file reading process.");
//        }
//    }

    public void exportToCSVFile(List<Employee> employees) {
        File file = new File(this.path + this.fileName);
        if (!file.exists()) {
            try {
//                todo: stopped here, make the creating of file from scratch working
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

//    private Employee convertFromCSVToEmployee(String csvLine) {
//              /*
//        int id - unique, gestionated from data base
//        string name
//        string surname
//        string idnp - unique, gestionated from app
//        LocalDate birthDate
//        LocalDate engagedOn
//        double salary
//        Job job - Job is a enum with jobs
//         */
//
//        String[] parameters = csvLine.split(",");
//
//        String name = parameters[0];
//        String surname = parameters[1];
//        String idnp = parameters[2];
//        LocalDate birthDate = LocalDate.parse(parameters[3]);
//        double salary = Double.parseDouble(parameters[4]);
//        Job job = Job.valueOf(parameters[5]);
//
//        return new Employee(name, surname, idnp, birthDate, salary, job);
//    }
}
