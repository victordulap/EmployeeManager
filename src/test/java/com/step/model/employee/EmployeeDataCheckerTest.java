package com.step.model.employee;

import junit.framework.TestCase;
import org.junit.Test;

public class EmployeeDataCheckerTest {
    @Test
    public void testCheckIfIDNPValid() throws Exception {
        EmployeeDataChecker edc = new EmployeeDataChecker();

        TestCase.assertTrue("Testing if we can add a valid IDNP", edc.checkIfIDNPValid("1231231231230", Employee.getDummyEmployees()));
        TestCase.assertFalse("Can't add null indp", edc.checkIfIDNPValid(null, Employee.getDummyEmployees()));
        TestCase.assertFalse("IDNP must contain only numbers", edc.checkIfIDNPValid("asd", Employee.getDummyEmployees()));
        TestCase.assertFalse("IDNP must contain 13 symbols", edc.checkIfIDNPValid("123", Employee.getDummyEmployees()));
        TestCase.assertFalse("IDNP must not be repetitive", edc.checkIfIDNPValid("1234567890123", Employee.getDummyEmployees()));
    }

    @Test
    public void testCheckIfNameValid() throws Exception {
        EmployeeDataChecker edc = new EmployeeDataChecker();

        TestCase.assertTrue("Testing if we can add a valid name", edc.checkIfNameValid("Test"));
        TestCase.assertFalse("Name contains space", edc.checkIfNameValid(" Te st "));
        TestCase.assertFalse("Name is empty", edc.checkIfNameValid(""));
        TestCase.assertFalse("Name contains only spaces", edc.checkIfNameValid("   "));
        TestCase.assertFalse("Name contains symbols", edc.checkIfNameValid(".;sdaf.;"));
        TestCase.assertFalse("Null name", edc.checkIfNameValid(null));
    }
}
