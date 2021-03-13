package com.step.model.employee.search;

import com.step.model.employee.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeFilter {
    public List<Employee> filterById(int minId, int maxId, List<Employee> empsToFilter) {
        return empsToFilter.stream()
                .filter(e -> e.getId() >= minId && e.getId() <= maxId)
                .collect(Collectors.toList());
    }

    public List<Employee> filterByIDNP(String idnp, List<Employee> empsToFilter) {
        return empsToFilter.stream()
                .filter(e -> e.getIdnp().matches(".*" + idnp + ".*")) // similar to %LIKE% in SQL
                .collect(Collectors.toList());
    }

    public List<Employee> filterByName(String name, List<Employee> empsToFilter) {
        return empsToFilter.stream()
                .filter(e -> e.getName().matches("(?i).*" + name + ".*")) // (?i) - case ignore
                .collect(Collectors.toList());
    }

    public List<Employee> filterBySurname(String surname, List<Employee> empsToFilter) {
        return empsToFilter.stream()
                .filter(e ->  e.getSurname().matches("(?i).*" + surname + ".*"))
                .collect(Collectors.toList());
    }

    public List<Employee> filterByBirthDate(LocalDate minBirthDate, LocalDate maxBirthDate, List<Employee> empsToFilter) {
        return empsToFilter.stream()
                .filter(e -> e.getBirthDate().isAfter(minBirthDate)
                        && e.getBirthDate().isBefore(maxBirthDate))
                .collect(Collectors.toList());
    }

    public List<Employee> filterByEngagedOn(LocalDate minEngagedOn, LocalDate maxEngagedON, List<Employee> empsToFilter) {
        return empsToFilter.stream()
                .filter(e -> e.getEngagedOn().isAfter(minEngagedOn)
                        && e.getEngagedOn().isBefore(maxEngagedON))
                .collect(Collectors.toList());
    }

    public List<Employee> filterBySalary(Double minSalary, Double maxSalary, List<Employee> empsToFilter) {
        return empsToFilter.stream()
                .filter(e -> e.getSalary() >= minSalary && e.getId() <= maxSalary)
                .collect(Collectors.toList());
    }
}
