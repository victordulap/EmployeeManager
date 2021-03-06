package com.step.model.employee.search;

public class EmployeeSearchConditions {
//    sort (there should be only one sort condition, thats why we reset it to null in a setter)
    private Boolean salaryAsc;
    private Boolean birthDateAsc;
//    filter

    public EmployeeSearchConditions() {
        this.salaryAsc = null;
        this.birthDateAsc = null;
    }

    public Boolean getSalaryAsc() {
        return salaryAsc;
    }

    public void setSalaryAsc(Boolean salaryAsc) {
        this.salaryAsc = salaryAsc;
        // null reason explained in first lines of class
        this.birthDateAsc = null;
    }

    public Boolean getBirthDateAsc() {
        return birthDateAsc;
    }

    public void setBirthDateAsc(Boolean birthDateAsc) {
        this.birthDateAsc = birthDateAsc;
        // null reason explained in first lines of class
        this.salaryAsc = null;
    }
}
