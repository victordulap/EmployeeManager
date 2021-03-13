package com.step.model.employee;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class EmployeeDataCheckerTest {
    @Test
    public void testCheckIfIDNPValid() {
        EmployeeDataChecker edc = new EmployeeDataChecker();

        TestCase.assertTrue("Testing if we can add a valid IDNP", edc.checkIfIDNPFromStringIsValid("1231231231230", Employee.getDummyEmployees(), true));
        TestCase.assertFalse("Can't add null indp", edc.checkIfIDNPFromStringIsValid(null, Employee.getDummyEmployees(), true));
        TestCase.assertFalse("IDNP must contain only numbers", edc.checkIfIDNPFromStringIsValid("asd", Employee.getDummyEmployees(), true));
        TestCase.assertFalse("IDNP must contain 13 symbols", edc.checkIfIDNPFromStringIsValid("123", Employee.getDummyEmployees(), true));
        TestCase.assertFalse("IDNP must not be repetitive", edc.checkIfIDNPFromStringIsValid("1234567890123", Employee.getDummyEmployees(), true));
        TestCase.assertFalse("IDNP cant be empty", edc.checkIfIDNPFromStringIsValid("", Employee.getDummyEmployees(), true));
    }

    @Test
    public void testCheckIfNameValid() {
        EmployeeDataChecker edc = new EmployeeDataChecker();

        TestCase.assertTrue("Testing if we can add a valid name", edc.checkIfNameValid("Test"));
        TestCase.assertFalse("Name contains space", edc.checkIfNameValid(" Te st "));
        TestCase.assertFalse("Name is empty", edc.checkIfNameValid(""));
        TestCase.assertFalse("Name contains only spaces", edc.checkIfNameValid("   "));
        TestCase.assertFalse("Name contains symbols", edc.checkIfNameValid(".;sdaf.;"));
        TestCase.assertFalse("Null name", edc.checkIfNameValid(null));
    }

    @Test
    public void testCheckIfDateFromStringValid() {
        EmployeeDataChecker edc = new EmployeeDataChecker();

        TestCase.assertTrue("Testing if we can a valid date", edc.checkIfDateFromStringValid("31.12.1999"));
        TestCase.assertFalse("Testing if we cant add a invalid date (month > 12)", edc.checkIfDateFromStringValid("31.13.1999"));
        TestCase.assertFalse("Testing if we cant add a invalid date (date > 31)", edc.checkIfDateFromStringValid("32.12.1999"));
        TestCase.assertFalse("Testing if we cant add a invalid date (invalid format)", edc.checkIfDateFromStringValid("32-12-1999"));
        TestCase.assertFalse("Testing if we cant add a invalid date (year not 4 digit)", edc.checkIfDateFromStringValid("31-12-999"));
        TestCase.assertFalse("Testing if we cant add a invalid date (month not 2 digit)", edc.checkIfDateFromStringValid("31-2-1999"));
        TestCase.assertFalse("Testing if we cant add a invalid date (day not 2 digit)", edc.checkIfDateFromStringValid("1-12-1999"));
        TestCase.assertFalse("Testing if we cant add null", edc.checkIfDateFromStringValid(null));
        TestCase.assertFalse("Testing if we cant add empty date", edc.checkIfDateFromStringValid(""));

//        for (int i = 2000; i > 0; i--) {
//        TestCase.assertTrue("Testing if we can add a old valid date (year " + i + ")", edc.checkIfDateValid("31.12." + i + ""));
//        }
    }

    @Test
    public void testCheckIfDoubleFromStringValid() {
        EmployeeDataChecker edc = new EmployeeDataChecker();

        TestCase.assertTrue("Testing if we can add a valid double number", edc.checkIfDoubleFromStringValid("1234.5"));
        TestCase.assertTrue("Testing if we can add a valid negative double number", edc.checkIfDoubleFromStringValid("-1234.5"));
        TestCase.assertTrue("Testing if we can add a valid int number", edc.checkIfDoubleFromStringValid("100"));
        TestCase.assertFalse("Testing if we cant add a invalid number", edc.checkIfDoubleFromStringValid("asdf123;asdf"));
        TestCase.assertFalse("Testing if we cant add null", edc.checkIfDoubleFromStringValid(null));
        TestCase.assertFalse("Testing if we cant add empty number", edc.checkIfDoubleFromStringValid(""));
        TestCase.assertTrue("Testing if we can add a number starting with dot (.)", edc.checkIfDoubleFromStringValid(".5"));
        TestCase.assertTrue("Testing if we can add a number ending with dot (.)", edc.checkIfDoubleFromStringValid("5."));
        TestCase.assertFalse("Testing if we cant add a number with 2 dots (.)", edc.checkIfDoubleFromStringValid("5..5"));
        TestCase.assertFalse("Testing if we cant add a number with comma (,) instead of dot (.)", edc.checkIfDoubleFromStringValid("1234,5"));
    }

    @Test
    public void testCheckIfIntFromStringValid() {
        EmployeeDataChecker edc = new EmployeeDataChecker();

        TestCase.assertTrue("Testing if we can add a valid int number", edc.checkIfIntFromStringValid("100"));
        TestCase.assertTrue("Testing if we can add a valid negative int number", edc.checkIfIntFromStringValid("-100"));
        TestCase.assertFalse("Testing if we cant add a invalid number", edc.checkIfIntFromStringValid("asdf123;asdf"));
        TestCase.assertFalse("Testing if we cant add null", edc.checkIfIntFromStringValid(null));
        TestCase.assertFalse("Testing if we cant add empty number", edc.checkIfIntFromStringValid(""));
        TestCase.assertFalse("Testing if we cant add a number starting with dot (.)", edc.checkIfIntFromStringValid(".5"));
        TestCase.assertFalse("Testing if we cant add a number ending with dot (.)", edc.checkIfIntFromStringValid("5."));
        TestCase.assertFalse("Testing if we cant add a number with 2 dots (.)", edc.checkIfIntFromStringValid("5..5"));
        TestCase.assertFalse("Testing if we cant add a number with comma (,) instead of dot (.)", edc.checkIfIntFromStringValid("1234,5"));
    }

    @Test
    public void testCheckIfJobFromStringValid() {
        EmployeeDataChecker edc = new EmployeeDataChecker();

        TestCase.assertTrue("Testing if we can add a valid job", edc.checkIfJobFromStringValid("PROGRAMMER"));
        TestCase.assertTrue("Testing if we can add a valid job with lowerCase", edc.checkIfJobFromStringValid("programmer"));
        TestCase.assertFalse("Testing if we cant add a valid job with space at end and start", edc.checkIfJobFromStringValid(" programmer "));
        TestCase.assertFalse("Testing if we cant add null", edc.checkIfJobFromStringValid(null));
        TestCase.assertFalse("Testing if we cant add empty job", edc.checkIfJobFromStringValid(""));
        TestCase.assertFalse("Testing if we cant add a invalid job", edc.checkIfJobFromStringValid("invalid job"));
    }

    @Test
    public void testCheckIfIDNPIsUnique() {
        EmployeeDataChecker edc = new EmployeeDataChecker();

        List<Employee> dummyEmps = Employee.getDummyEmployees();

        TestCase.assertFalse("Test if we cant add a repetitive idnp", edc.checkIfIDNPIsUnique(dummyEmps.get(0).getIdnp(), dummyEmps));
        TestCase.assertTrue("Test if we can add a unique idnp", edc.checkIfIDNPIsUnique("12309887672341", dummyEmps));
    }
}
