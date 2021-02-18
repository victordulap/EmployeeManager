package com.step.model.employee;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFinderTest {
    @Test
    public void testFindByIdnp() {
        EmployeeFinder ef = new EmployeeFinder();
        List<Employee> emps = new ArrayList<>(Employee.getDummyEmployees());

        TestCase.assertEquals("Test if can find a user by valid id",
                emps.stream().filter(e -> e.getId().equals(0)).findAny().orElse(null), // expected
                ef.findById(emps, 0)); // got
        TestCase.assertNotSame("Test if cant find a user by non-existing id",
                emps.stream().filter(e -> e.getId().equals(0)).findAny().orElse(null), // expected
                ef.findById(emps, 123123)); // got
    }
}
