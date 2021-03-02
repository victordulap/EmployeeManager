package com.step.app;

import com.step.model.employee.Employee;
import com.step.model.employee.consoleData.EmployeeOutputInConsole;
import com.step.model.employee.manager.EmployeeManager;
import com.step.model.employee.manager.csv.EmployeeManagerInCSVFile;
import com.step.model.employee.manager.db.EmployeeManagerInDB;
import com.step.model.employee.manager.json.EmployeeManagerInJSONFile;
import com.step.model.employee.manager.memory.EmployeeManagerInMemory;
import com.step.model.employee.manager.serialized.EmployeeManagerInSerializedFile;
import com.step.model.employee.manager.xml.EmployeeManagerInXMLFile;
import com.step.utilities.Utilities;

import java.util.Scanner;

public class Menu {
    private EmployeeManager em = null;
    private EmployeeOutputInConsole empShow = new EmployeeOutputInConsole();
    private Utilities util = new Utilities();

//    {
//        em.getEmployees().addAll(Employee.getDummyEmployees());
//    }

    private void selectAppModeMenu() {
        Scanner sc = new Scanner(System.in);
        boolean modeChosen = false;
        String nav = "";

        util.clearScreen();

        while(!modeChosen) {
            System.out.println("EMPLOYEE MANAGEMENT, choose data processing mode:");
            System.out.println();
            System.out.println("\t1. Demo (no saving at all)");
            System.out.println("\t2. CSV file");
            System.out.println("\t3. Serialized file");
            System.out.println("\t4. XML file");
            System.out.println("\t5. JSON file");
            System.out.println("\t6. Data base");
            System.out.println();
            System.out.println("\t0. exit");

            System.out.print("\nenter submenu number: ");
            nav = sc.nextLine();

            switch (nav) {
                case "1":
                    em = new EmployeeManagerInMemory();
                    em.getEmployees().addAll(Employee.getDummyEmployees());
                    modeChosen = true;
                    break;
                case "2":
                    em = new EmployeeManagerInCSVFile();
                    modeChosen = true;
                    break;
                case "3":
                    em = new EmployeeManagerInSerializedFile();
                    modeChosen = true;
                    break;
                case "4":
                    em = new EmployeeManagerInXMLFile();
                    modeChosen = true;
                    break;
                case "5":
                    em = new EmployeeManagerInJSONFile();
                    modeChosen = true;
                    break;
                case "6":
                    em = new EmployeeManagerInDB();
                    modeChosen = true;
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
        };
    }

    public void startMenu() {
        this.selectAppModeMenu();

        Scanner sc = new Scanner(System.in);
        String nav = "";

        // on start, load emps from file
        System.out.println("Loading data...");
        em.load();
        System.out.println("Loading data done!");

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
                    boolean empToEditNotFound = true;

                    while (empToEditNotFound) {
                        try {
                            Employee empToEdit = this.findMenu();
                            if (empToEdit != null) {
                                empToEditNotFound = false;

                                Employee newEmp = empShow.editEmployee(empToEdit);
                                em.edit(empToEdit.getId(), newEmp);
                            } else {
                                System.out.println("Employee not found.");
                                util.enterAnyValueToContinue();
                            }
                        } catch (Exception e) {
                            System.out.println("Exiting submenu...");
                            empToEditNotFound = false;
                        }
                    }
                    break;
                case "4":
                    boolean empToDelNotFound = true;
                    while (empToDelNotFound) {
                        try {
                            Employee empToDelete = this.findMenu();
                            if (empToDelete != null) {
                                empToDelNotFound = false;

                                em.delete(empToDelete.getId());
                                System.out.println("Deleted employee " + empToDelete.getName() + " " + empToDelete.getSurname() +
                                        " (" + empToDelete.getId() + "/" + empToDelete.getIdnp() + ") with success!");
                            } else {
                                System.out.println("Employee not found.");
                                util.enterAnyValueToContinue();
                            }
                        } catch (Exception e) {
                            System.out.println("Exiting submenu...");
                            empToDelNotFound = false;
                        }
                    }
                    break;
                case "0":
                    util.clearScreen();
                    System.out.println("Saving data...");
                    em.save();
                    System.out.println("Saving done!");
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

    private Employee findMenu() throws Exception {
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
            System.out.println();
            System.out.println("\t0. exit");

            System.out.print("\nenter submenu number: ");
            nav = sc.nextLine();

            switch (nav) {
                case "1":
                    return empShow.getEmployeeById(em.getEmployees());
                case "2":
                    return empShow.getEmployeeByIdnp(em.getEmployees());
                case "3":
                    return empShow.getEmployeeByName(em.getEmployees());
                case "0":
                    throw new Exception("Quited menu");

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
