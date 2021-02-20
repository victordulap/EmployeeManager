package com.step.model.employee;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFinderTest {

    @Before
    public void init() {
        Employee.resetId();
    }

    @Test
    public void testFindById() {
        EmployeeFinder ef = new EmployeeFinder();
        List<Employee> emps = new ArrayList<>(Employee.getDummyEmployees());


        TestCase.assertEquals("Test if can find a user by valid id",
                emps.stream().filter(e -> e.getId().equals(0)).findAny().orElse(null), // expected
                ef.findById(emps, 0)); // got

        TestCase.assertNotSame("Test if cant find a user by non-existing id",
                emps.stream().filter(e -> e.getId().equals(0)).findAny().orElse(null), // expected
                ef.findById(emps, 12301)); // got
    }

    @Test
    public void testFindByIdnp() {
        EmployeeFinder ef = new EmployeeFinder();
        List<Employee> emps = new ArrayList<>(Employee.getDummyEmployees());


        TestCase.assertEquals("Test if can find a user by valid idnp",
                emps.stream().filter(e -> e.getIdnp().equals("1234567890123")).findAny().orElse(null), // expected
                ef.findByIdnp(emps, "1234567890123")); // got

        TestCase.assertNotSame("Test if cant find a user by non-existing idnp",
                emps.stream().filter(e -> e.getIdnp().equals("1234567890123")).findAny().orElse(null), // expected
                ef.findByIdnp(emps, "1234567890321")); // got
    }

    @Test
    public void testFindByNameAndSurname() {
        EmployeeFinder ef = new EmployeeFinder();
        List<Employee> emps = new ArrayList<>(Employee.getDummyEmployees());


        try {
            TestCase.assertEquals("Test if can find a user by name and surname",
                    emps.stream().filter(e -> e.getName().equals("Victor") && e.getSurname().equals("Dulap")).findAny().orElse(null), // expected
                    ef.findByNameAndSurname(emps, "Victor", "Dulap")); // got
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            TestCase.assertNull("Test if cant find a user by non existing name and surname",
                    ef.findByNameAndSurname(emps, "Victor", "NoNameLikeThat")); // got
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Employee byNameAndSurname = ef.findByNameAndSurname(emps, "Ion", "Alb");
            System.out.println(byNameAndSurname);
            TestCase.assertNotNull("Test if cant find a employee that has repetitive name and surname with other employee",
                   byNameAndSurname); // got
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}
