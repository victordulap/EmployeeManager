package com.step.app;

import com.step.utilities.Utilities;
import com.step.model.employee.Employee;
import com.step.model.employee.consoleData.EmployeeOutputInConsole;
import com.step.model.employee.Job;
import com.step.model.employee.manager.EmployeeManager;
import com.step.model.employee.manager.EmployeeManagerInMemory;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
//    todo: make the user to choose the EmployeeManager method of working
    private EmployeeManager em = new EmployeeManagerInMemory();
    private EmployeeOutputInConsole empShow = new EmployeeOutputInConsole();
    private Utilities util = new Utilities();

    public void startMenu() {
        Scanner sc = new Scanner(System.in);
        String nav = "";

        util.clearScreen();

        do {
            System.out.println("EMPLOYEE MANAGEMENT");
            System.out.println();
            System.out.println("\t1. view");
            System.out.println("\t2. insert");
            System.out.println("\t3. update");
            System.out.println("\t4. delete");
//            System.out.println("\t5. file");
            System.out.println();
            System.out.println("\t0. exit");

            System.out.print("\nenter submenu number: ");
            nav = sc.nextLine();

            switch (nav) {
                case "1":
                    empShow.showEmployeesInTable(em.getEmployees());
                    break;
                case "2":
                        empShow.insert(em.getEmployees());
//                    em.insert(new Employee("Victor", "Dulap", "1234567890123", LocalDate.now(), (double) 1000, Job.PROGRAMMER));
                    break;
                case "3":
                    em.edit(0, new Employee("Edited", "Employee", LocalDate.now(), (double) 1000, Job.PROGRAMMER));
                    break;
                case "4":
                    em.delete(0);
                    break;
                case "0":
                    util.clearScreen();
                    System.out.println("Application closed!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nNo such submenu, try again (ex: 1)");
                    util.enterAnyValueToContinue();
                    break;
            }

            util.clearScreen();
        } while (!nav.equals("0"));
    }
}
