package com.step.model.employee.consoleData;

import com.step.model.employee.Employee;
import com.step.model.employee.EmployeeFinder;
import com.step.model.employee.Job;
import com.step.utilities.Utilities;

import java.time.LocalDate;
import java.util.List;

public class EmployeeOutputInConsole {
    private Utilities util = new Utilities();
    private EmployeeInputFromConsole eifc = new EmployeeInputFromConsole();
    private EmployeeFinder employeeFinder = new EmployeeFinder();

    public void showEmployeesInTable(List<Employee> employees) {
        util.clearScreen();

        if (employees.size() > 0) {
            System.out.println("EMPLOYEE LIST: \n");
        } else {
            System.out.println("NO EMPLOYEES FOUND!");
            util.enterAnyValueToContinue();
            return;
        }

        char cornerTopLeft = '\u2554';
        char cornerTopRight = '\u2557';
        char cornerBottomLeft = '\u255A';
        char cornerBottomRight = '\u255D';
        char lineX = '\u2550';
        char lineY = '\u2551';
        char cross = '\u256C';
        char crossTDown = '\u2566';
        char crossTUp = '\u2569';
        char crossTLeft = '\u2563';
        char crossTRight = '\u2560';

        int employeesSize = employees.size();
        int iN = employeesSize > 1 ? employeesSize : 2;

        int idN = 2;
        int nameN = 4;
        int surnameN = 7;
        int birthDateN = 10;
        int engagedOnN = 10;
        int idnpN = 13;
        int salaryN = 10;
        int jobN = 3;
        for (int i = 0; i < employeesSize; i++) {
            Employee currentEmployee = employees.get(i);

            if (currentEmployee.getId() > idN) {
                idN = currentEmployee.getId();
            }
            if (currentEmployee.getName().length() > nameN) {
                nameN = currentEmployee.getName().length();
            }
            if (currentEmployee.getSurname().length() > surnameN) {
                surnameN = currentEmployee.getSurname().length();
            }
            if (String.valueOf(currentEmployee.getSalary()).length() > salaryN) {
                salaryN = String.valueOf(currentEmployee.getSalary()).length();
            }
            if (currentEmployee.getJob().toString().length() > jobN) {
                jobN = currentEmployee.getJob().toString().length();
            }
        }

        System.out.println(cornerTopLeft + util.generateMultipleChars(iN, lineX) + crossTDown // i
                + util.generateMultipleChars(idN, lineX) + crossTDown //id
                + util.generateMultipleChars(nameN, lineX) + crossTDown //name
                + util.generateMultipleChars(surnameN, lineX) + crossTDown // surname
                + util.generateMultipleChars(birthDateN, lineX) + crossTDown // birth date
                + util.generateMultipleChars(engagedOnN, lineX) + crossTDown // engagedOn
                + util.generateMultipleChars(idnpN, lineX) + crossTDown // idnp
                + util.generateMultipleChars(salaryN, lineX) + crossTDown // salary
                + util.generateMultipleChars(jobN, lineX) // job
                + cornerTopRight);
        System.out.println(lineY + util.insertWordWithNSpaces(iN, "nr") + lineY
                + util.insertWordWithNSpaces(idN, "id") + lineY
                + util.insertWordWithNSpaces(nameN, "name") + lineY
                + util.insertWordWithNSpaces(surnameN, "surname") + lineY
                + util.insertWordWithNSpaces(birthDateN, "birth date") + lineY
                + util.insertWordWithNSpaces(engagedOnN, "engaged on") + lineY
                + util.insertWordWithNSpaces(idnpN, "idnp") + lineY
                + util.insertWordWithNSpaces(salaryN, "salary ($)") + lineY
                + util.insertWordWithNSpaces(jobN, "job") + lineY);

        for (int i = 0; i < employeesSize; i++) {
            System.out.println(crossTRight + util.generateMultipleChars(iN, lineX) + cross // i
                    + util.generateMultipleChars(idN, lineX) + cross //id
                    + util.generateMultipleChars(nameN, lineX) + cross //name
                    + util.generateMultipleChars(surnameN, lineX) + cross // surname
                    + util.generateMultipleChars(birthDateN, lineX) + cross // birth date
                    + util.generateMultipleChars(engagedOnN, lineX) + cross // engagedOn
                    + util.generateMultipleChars(idnpN, lineX) + cross // idnp
                    + util.generateMultipleChars(salaryN, lineX) + cross // salary
                    + util.generateMultipleChars(jobN, lineX) // job
                    + crossTLeft);

            System.out.println(lineY + util.insertWordWithNSpaces(iN, String.valueOf(i + 1)) + lineY
                    + util.insertWordWithNSpaces(idN, String.valueOf(employees.get(i).getId())) + lineY
                    + util.insertWordWithNSpaces(nameN, employees.get(i).getName()) + lineY
                    + util.insertWordWithNSpaces(surnameN, employees.get(i).getSurname()) + lineY
                    + util.insertWordWithNSpaces(birthDateN, employees.get(i).getBirthDateFormatted()) + lineY
                    + util.insertWordWithNSpaces(engagedOnN, employees.get(i).getEngagedOnFormatted()) + lineY
                    + util.insertWordWithNSpaces(idnpN, employees.get(i).getIdnp()) + lineY
                    + util.insertWordWithNSpaces(salaryN, String.valueOf(employees.get(i).getSalary())) + lineY
                    + util.insertWordWithNSpaces(jobN, (util.firstLetterUpperCase(employees.get(i).getJob().toString()))) + lineY);
        }

        System.out.println(cornerBottomLeft + util.generateMultipleChars(iN, lineX) + crossTUp // i
                + util.generateMultipleChars(idN, lineX) + crossTUp //id
                + util.generateMultipleChars(nameN, lineX) + crossTUp //name
                + util.generateMultipleChars(surnameN, lineX) + crossTUp // surname
                + util.generateMultipleChars(birthDateN, lineX) + crossTUp // birth date
                + util.generateMultipleChars(engagedOnN, lineX) + crossTUp // engagedOn
                + util.generateMultipleChars(idnpN, lineX) + crossTUp // idnp
                + util.generateMultipleChars(salaryN, lineX) + crossTUp // salary
                + util.generateMultipleChars(jobN, lineX) // job
                + cornerBottomRight);

        util.enterAnyValueToContinue();
    }

    /**
     * basically used to get a new employee and send it to EmployeeManager.insert()
     *
     * @param employees to check in this list if found a repetitive idnp
     */
    public Employee getNewEmployee(List<Employee> employees) {
        String name = eifc.readName("Enter name: ");
        String surname = eifc.readName("Enter surname: ");
        String idnp = eifc.readIdnp("Enter idnp: ", employees, true);
        LocalDate birthDate = eifc.readBirthDate("Enter birth date: ");
        Double salary = eifc.readDouble("Enter salary: ");
        Job job = eifc.readJob("Enter job: ");

        return new Employee(name, surname, idnp, birthDate, salary, job);
    }

    /**
     * @param oldEmp used to show old data for comfort
     * @return a new employee with edited data
     */
    public Employee editEmployee(Employee oldEmp) {
        String name = eifc.readName("Enter name: " + oldEmp.getName() + " -> ");
        String surname = eifc.readName("Enter surname: " + oldEmp.getSurname() + " -> ");
        Double salary = eifc.readDouble("Enter salary: " + oldEmp.getSalary() + " -> ");
        Job job = eifc.readJob("Enter job: " + oldEmp.getJob() + " -> ");

        return new Employee(name, surname, salary, job);
    }

    public Employee getEmployeeById(List<Employee> employees) {
        boolean employeeFound = false;
        Employee empById = null;

        while (!employeeFound) {
            Integer id = eifc.readId("Enter id: ");

            empById = employeeFinder.findById(employees, id);

            if (empById != null) {
                employeeFound = true;
            } else {
                System.out.println("Employee not found, try again...");
            }
        }

        return empById;
    }

    public Employee getEmployeeByIdnp(List<Employee> employees) {
        boolean employeeFound = false;
        Employee empByIdnp = null;

        while (!employeeFound) {
            String idnp = eifc.readIdnp("Enter idnp: ", employees, false);

            empByIdnp = employeeFinder.findByIdnp(employees, idnp);

            if (empByIdnp != null) {
                employeeFound = true;
            } else {
                System.out.println("Employee not found, try again...");
            }
        }

        return empByIdnp;
    }
}
