package com.step.model.employee;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class EmployeeDataChecker {
    /**
     * also checks if idnp is not repetitive
     *
     * @param employees employees to check in, if idnp is repetitive
     */
    public boolean checkIfIDNPValid(String idnp, List<Employee> employees) {
//        if (idnp == null) {
//            throw new Exception("IDNP cannot be null");
//        } else if (!idnp.matches("[0-9]+")) {
//            throw new Exception("IDNP must contain only numbers");
//        } else if (idnp.length() != 13) {
//            throw new Exception("IDNP must contain 13 symbols");
//        }
        if (idnp == null || !idnp.matches("[0-9]+") || idnp.length() != 13 || checkIfIDNPRepetitive(idnp, employees)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkIfIDNPRepetitive(String idnp, List<Employee> employees) {
        boolean isIDNPUnique = employees.stream()
                .map(e -> e.getIdnp())
                .anyMatch(empIdnp -> empIdnp.equals(idnp));

        return isIDNPUnique;
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
