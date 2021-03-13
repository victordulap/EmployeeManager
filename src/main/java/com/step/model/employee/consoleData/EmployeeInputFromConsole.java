package com.step.model.employee.consoleData;

import com.step.model.employee.Employee;
import com.step.model.employee.EmployeeDataChecker;
import com.step.model.employee.Job;
import com.step.utilities.Utilities;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EmployeeInputFromConsole {
    /*
    - int id - unique, gestionated from data base
	+ string name
	+ string surname
    + string idnp - unique, gestionated from app
    + LocalDate birthDate
    - LocalDate engagedOn
    + double salary
    + Job job - Job is a enum with jobs
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
            String name = this.readString(message);

            if (edc.checkIfNameValid(name)) {
                name = util.firstLetterUpperCase(name);
                return name;
            } else {
                this.showError("Name invalid, try again... (ex of valid name: \"John\")");
            }
        }
    }

    /**
     * @param message   text to show in console, ex:
     *                  message = "Enter name"
     *                  output: Enter name: (input form keyboard)
     * @param checkIfIdnpUnique to run the method of checking repetitive idnp
     * @param employees to check in if found a repetitive idnp
     * @return valid name with first letter upper case
     */
    public String readIdnp(String message, List<Employee> employees, boolean checkIfIdnpUnique) {
        while (true) {
            String idnp = this.readString(message);

            if (edc.checkIfIDNPFromStringIsValid(idnp)) {
                if (checkIfIdnpUnique) {
                    if (edc.checkIfIDNPIsUnique(idnp, employees)) {
                        return idnp;
                    } else {
                        this.showError("IDNP invalid, it should be unique, try again...");
                    }
                } else {
                    return idnp;
                }
            } else {
                this.showError("IDNP invalid, should contain strictly 13 numbers, try again... (ex of valid idnp: \"1234567890123\")");
            }
        }
    }

    /**
     * @param message text to show in console, ex:
     *                message = "Enter name"
     *                output: Enter name: (input form keyboard)
     * @return valid name with first letter upper case
     */
    public LocalDate readBirthDate(String message) {
        while (true) {
            String birthDate = this.readString(message);

            if (edc.checkIfDateFromStringValid(birthDate)) {
                return LocalDate.parse(birthDate, Employee.dateTimeFormatter);
            } else {
                this.showError("Date invalid, should be formatted in \"dd.mm.yyyy\", try again... (ex of valid date: \"20.02.1999\")");
            }
        }
    }

    public Double readDouble(String message) {
        while (true) {
            String number = this.readString(message);

            if (edc.checkIfDoubleFromStringValid(number)) {
                return Double.parseDouble(number);
            } else {
                this.showError("Number invalid, try again... (ex of valid number: \"1234.5\")");
            }
        }
    }

    public Integer readId(String message) {
        while (true) {
            String id = this.readString(message);

            if (edc.checkIfIntFromStringValid(id)) {
                return Integer.parseInt(id);
            } else {
                this.showError("ID invalid, try again... (ex of valid ID: \"1\")");
            }
        }
    }

    public Job readJob(String message) {
        while (true) {
            String job = this.readString(message);

            if (edc.checkIfJobFromStringValid(job)) {
                return Job.valueOf(job.toUpperCase());
            } else {
                this.showError("Job invalid, try again... (job list: " + Arrays.toString(Job.values()) + ")");
            }
        }
    }

    private String readString(String message) {
            Scanner sc = new Scanner(System.in);

            System.out.print(message);
            String str = sc.nextLine();

            return str.trim();
    }

    private void showError(String errorMessage) {
        System.out.println(errorMessage);
        util.enterAnyValueToContinue();
    }
}
