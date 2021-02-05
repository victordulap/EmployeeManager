package com.step.model.employee.consoleData;

import com.step.model.employee.Employee;
import com.step.model.employee.EmployeeDataChecker;
import com.step.utilities.Utilities;

import java.util.List;
import java.util.Scanner;

public class EmployeeInputFromConsole {
    /*
    int id - unique, gestionated from data base
	+ string name
	+ string surname
    string idnp - unique, gestionated from app
    LocalDate birthDate
    LocalDate engagedOn
    double salary
    Job job - Job is a enum with jobs
     */

    private Utilities util = new Utilities();
    private EmployeeDataChecker edc = new EmployeeDataChecker();

    /**
     * @param message text to show in console, ex:
     *                message = "Enter name"
     *                output: Enter name: (input form keyboard)
     * @return valid name with first letter upper case
     */
    public String readName(String message) {
        while (true) {
            Scanner sc = new Scanner(System.in);

            System.out.print(message);
            String name = sc.nextLine();

            name = name.trim();

            if (edc.checkIfNameValid(name)) {
                name = util.firstLetterUpperCase(name);
                return name;
            } else {
                System.out.println("Name invalid, try again... (ex of valid name: \"John\")");
                util.enterAnyValueToContinue();
            }
        }
    }

    /**
     * @param message text to show in console, ex:
     *                message = "Enter name"
     *                output: Enter name: (input form keyboard)
     * @param employees to check in if found a repetitive idnp
     * @return valid name with first letter upper case
     */
    public String readIdnp(String message, List<Employee> employees) {
        while (true) {
            Scanner sc = new Scanner(System.in);

            System.out.print(message);
            String idnp = sc.nextLine();

            idnp = idnp.trim();

            if (edc.checkIfIDNPValid(idnp, employees)) {
                return idnp;
            } else {
                System.out.println("IDNP invalid, should contain 13 numbers, try again... (ex of valid idnp: \"1234567890123\")");
                util.enterAnyValueToContinue();
            }
        }
    }

}
