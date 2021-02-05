package com.step.model.employee;

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
        if (idnp == null || !idnp.matches("[0-9]+") || idnp.length() != 13 || checkIfIDNPRepetitive(idnp,employees)) {
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
        if (name == null || !name.matches("^[\\p{L}]+$") ) {
            return false;
        } else {
            return true;
        }
    }
}
