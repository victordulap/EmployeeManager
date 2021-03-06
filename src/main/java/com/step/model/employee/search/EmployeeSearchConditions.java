package com.step.model.employee.search;

public class EmployeeSearchConditions {
    private Boolean salaryAsc;
    private Boolean birthDateAsc;

    public EmployeeSearchConditions() {
        this.salaryAsc = null;
        this.birthDateAsc = null;
    }

    public Boolean getSalaryAsc() {
        return salaryAsc;
    }

    public void setSalaryAsc(Boolean salaryAsc) {
        this.salaryAsc = salaryAsc;
        this.birthDateAsc = null;
    }

    public Boolean getBirthDateAsc() {
        return birthDateAsc;
    }

    public void setBirthDateAsc(Boolean birthDateAsc) {
        this.birthDateAsc = birthDateAsc;
        this.salaryAsc = null;
    }
}
