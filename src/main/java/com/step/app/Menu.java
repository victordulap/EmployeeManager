package com.step.app;

import com.step.model.employee.Employee;
import com.step.model.employee.consoleData.EmployeeOutputInConsole;
import com.step.model.employee.manager.EmployeeManager;
import com.step.model.employee.manager.EmployeeManagerInMemory;
import com.step.utilities.Utilities;

import java.util.Scanner;

public class Menu {
    //    todo: make the user to choose the EmployeeManager method of working
    private EmployeeManager em = new EmployeeManagerInMemory();
    private EmployeeOutputInConsole empShow = new EmployeeOutputInConsole();
    private Utilities util = new Utilities();

    {
        em.getEmployees().addAll(Employee.getDummyEmployees());
    }

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
                    em.insert(empShow.getNewEmployee(em.getEmployees()));
                    break;
                case "3":
                    Employee empToEdit = this.findMenu();
                    em.edit(empToEdit.getId(), empShow.editEmployee(empToEdit));
                    break;
                case "4":
                    Employee empToDelete = this.findMenu();
                    em.delete(empToDelete.getId());
                    System.out.println("Deleted employee " + empToDelete.getName() + empToDelete.getSurname() +
                            " (" + empToDelete.getId() + "/" + empToDelete.getIdnp() + ") with success!");
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

    private Employee findMenu() {
        Scanner sc = new Scanner(System.in);
        String nav = "";
        Employee foundEmp = null;

        util.clearScreen();

        do {
            System.out.println("Find by");
            System.out.println();
            System.out.println("\t1. id");
            System.out.println("\t2. idnp");
            System.out.println("\t3. name and surname");
//            System.out.println();
//            System.out.println("\t0. exit");

            System.out.print("\nenter submenu number: ");
            nav = sc.nextLine();

            switch (nav) {
                case "1":
                    return empShow.getEmployeeById(em.getEmployees());
                case "2":
                    return empShow.getEmployeeByIdnp(em.getEmployees());
                case "3":
                    break;
//                case "0":
//                    util.clearScreen();
//                    break;

                default:
                    System.out.println("\nNo such submenu, try again (ex: 1)");
                    util.enterAnyValueToContinue();
                    break;
            }

            util.clearScreen();
        } while (!nav.equals("0"));

        return foundEmp;
    }
}
