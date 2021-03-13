package com.step.model.employee;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class EmployeeDataChecker {
    /**
     * doesn't check if idnp is unique,
     * this.checkIfIDNPRepetitive(idnp, employees) is for checking that ^
     * @return true if idnp from string is valid, false if otherwise
     */
    public boolean checkIfIDNPFromStringIsValid(String idnp) {
        return idnp != null && idnp.length() == 13 && idnp.matches("[0-9]+");
    }

    /**
     * @param idnp idnp to check
     * @param employees list to check idnp repetitiveness in
     * @return true if idnp is unique, false if otherwise
     */
    public boolean checkIfIDNPIsUnique(String idnp, List<Employee> employees) {
        return employees.stream()
                .map(e -> e.getIdnp())
                .noneMatch(empIdnp -> empIdnp.equals(idnp));
    }

    public boolean checkIfNameValid(String name) {
//        \\p{L} is a Unicode Character Property that matches any kind of letter from any language
        if (name == null || name.length() <= 0 || !name.matches("^[\\p{L}]+$")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param date a string that should be formatted in dd.mm.yyyy
     * @return
     */
    public boolean checkIfDateFromStringValid(String date) {
        if (date == null || date.length() <= 0) {
            return false;
        } else {
            try {
                LocalDate d = LocalDate.parse(date, Employee.dateTimeFormatter);
                return true;
            } catch (DateTimeParseException exception) {
                return false;
            }
        }
    }

    public boolean checkIfDoubleFromStringValid(String stringNumber) {
        if (stringNumber == null || stringNumber.length() <= 0) {
            return false;
        } else {
            try {
                Double number = Double.parseDouble(stringNumber);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public boolean checkIfIntFromStringValid(String stringNumber) {
        if (stringNumber == null || stringNumber.length() <= 0) {
            return false;
        } else {
            try {
                Integer number = Integer.parseInt(stringNumber);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public boolean checkIfJobFromStringValid(String job) {
        if (job == null || job.length() <= 0) {
            return false;
        } else {
            try {
                Job j = Job.valueOf(job.toUpperCase());
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
}
