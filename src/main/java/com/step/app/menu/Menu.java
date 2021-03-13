package com.step.app.menu;

import com.step.model.employee.Employee;
import com.step.model.employee.consoleData.EmployeeInputFromConsole;
import com.step.model.employee.consoleData.EmployeeOutputInConsole;
import com.step.model.employee.manager.EmployeeManager;
import com.step.model.employee.manager.csv.EmployeeManagerInCSVFile;
import com.step.model.employee.manager.db.EmployeeManagerInDB;
import com.step.model.employee.manager.json.EmployeeManagerInJSONFile;
import com.step.model.employee.manager.memory.EmployeeManagerInMemory;
import com.step.model.employee.manager.serialized.EmployeeManagerInSerializedFile;
import com.step.model.employee.manager.xml.EmployeeManagerInXMLFile;
import com.step.model.employee.search.EmployeeFilter;
import com.step.model.employee.search.EmployeeSort;
import com.step.model.employee.search.EmployeeSortCondition;
import com.step.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private EmployeeManager em = null;
    private EmployeeOutputInConsole empOut = new EmployeeOutputInConsole();
    private EmployeeInputFromConsole empIn = new EmployeeInputFromConsole();
    private EmployeeSort empSort = new EmployeeSort();
    private Utilities util = new Utilities();

//    {
//        em.getEmployees().addAll(Employee.getDummyEmployees());
//    }

    private void selectAppModeMenu() {
        Scanner sc = new Scanner(System.in);
        boolean modeChosen = false;
        String nav = "";

        util.clearScreen();

        while (!modeChosen) {
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
        }
        ;
    }

    public void mainMenu() {
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
                    try {
                        this.viewMenu();
                    } catch (Exception e) {
                        System.out.println("Exiting submenu...");
                    }
                    break;
                case "2":
                    boolean inserted = em.insert(empOut.getNewEmployee(em.getEmployees()));
                    if (inserted) {
                        System.out.println("Employee inserted");
                    } else {
                        System.out.println("New employee wasn't inserted, idnp may be repetitive");
                    }
                    break;
                case "3":
                    boolean empToEditNotFound = true;

                    while (empToEditNotFound) {
                        try {
                            Employee empToEdit = this.findMenu();
                            if (empToEdit != null) {
                                empToEditNotFound = false;

                                Employee newEmp = empOut.editEmployee(empToEdit);
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
                    return empOut.getEmployeeById(em.getEmployees());
                case "2":
                    return empOut.getEmployeeByIdnp(em.getEmployees());
                case "3":
                    return empOut.getEmployeeByName(em.getEmployees());
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

    private void viewMenu() throws Exception {
        Scanner sc = new Scanner(System.in);
        String nav = "";

        EmployeeSortCondition sortCondition = null;
        boolean filterCondition = false;
        List<Employee> searchedEmps = em.getEmployees();

        util.clearScreen();

        do {
            System.out.println("View menu");
            System.out.println();
            System.out.println("\t1. All employees");
            System.out.println("\t2. Searched employees (by filtering and sorting conditions)");
            System.out.println("\t3. Filter by (note, if you want to apply same filter with different values, reset the conditions)");
            System.out.println("\t4. Sort by");
            System.out.println("\t5. Reset searching conditions");
            System.out.println();
            System.out.println("\t0. exit");

            System.out.print("\nenter submenu number: ");
            nav = sc.nextLine();

            switch (nav) {
                case "1":
                    empOut.showEmployeesInTable(em.getEmployees());
                    break;
                case "2":
                    // first filter, than sort
                    // todo: add filtering
                    // salary range, name and surname, birthdate range

                    // SORT
                    if (sortCondition != null) {
                        switch (sortCondition) {
                            case SALARY_ASC:
                                searchedEmps = empSort.sortBySalary(searchedEmps, true);
                                break;
                            case SALARY_DESC:
                                searchedEmps = empSort.sortBySalary(searchedEmps, false);
                                break;
                            case BIRTHDATE_ASC:
                                searchedEmps = empSort.sortByBirthDate(searchedEmps, true);
                                break;
                            case BIRTHDATE_DESC:
                                searchedEmps = empSort.sortByBirthDate(searchedEmps, false);
                                break;
                            case ENGAGEDON_ASC:
                                searchedEmps = empSort.sortByEngagedOn(searchedEmps, true);
                                break;
                            case ENGAGEDON_DESC:
                                searchedEmps = empSort.sortByEngagedOn(searchedEmps, false);
                                break;
                        }
                    }

                    if (sortCondition != null || filterCondition) {
                        empOut.showEmployeesInTable(searchedEmps);
                    } else {
                        empOut.showEmployeesInTable(null);
                    }
                    break;
                case "3":
                    searchedEmps = this.filterMenu(searchedEmps);
                    filterCondition = true;
                    break;
                case "4":
                    sortCondition = this.sortMenu();
                    break;
                case "5":
                    sortCondition = null;
                    filterCondition = false;
                    searchedEmps = em.getEmployees();
                    break;
                case "0":
                    throw new Exception("Quited menu");

                default:
                    System.out.println("\nNo such submenu, try again (ex: 1)");
                    util.enterAnyValueToContinue();
                    break;
            }

            util.clearScreen();
        } while (!nav.equals("0"));
    }


    private List<Employee> filterMenu(List<Employee> empsToFilter) {
        Scanner sc = new Scanner(System.in);
        String nav = "";
        boolean optionSelected = false;
        List<Employee> filteredEmployees = new ArrayList<>();

        util.clearScreen();

        while (!optionSelected) {
            System.out.println("Filter by");
            System.out.println();
            System.out.println("\t1. id");
            System.out.println("\t2. idnp");
            System.out.println("\t3. name");
            System.out.println("\t4. surname");
            System.out.println("\t5. birth date");
            System.out.println("\t6. engagement date");
            System.out.println("\t7. salary");

            System.out.print("\nenter submenu number: ");
            nav = sc.nextLine();

            switch (nav) {
                case "1":
                    Integer minId = empIn.readId("Enter min id: ");
                    Integer maxId = empIn.readId("Enter max id: ");
                    filteredEmployees = new EmployeeFilter().filterById(minId, maxId, empsToFilter);
                    optionSelected = true;
                    break;
                case "2":
                    String idnp = empIn.readString("Enter idnp (or a part of it): ");
                    filteredEmployees = new EmployeeFilter().filterByIDNP(idnp, empsToFilter);
                    optionSelected = true;
                    break;
                case "3":
                    String name = empIn.readString("Enter name (or a part of it): ");
                    filteredEmployees = new EmployeeFilter().filterByName(name, empsToFilter);
                    optionSelected = true;
                    break;
                case "4":
                    String surname = empIn.readString("Enter surname (or a part of it): ");
                    filteredEmployees = new EmployeeFilter().filterBySurname(surname, empsToFilter);
                    optionSelected = true;
                    break;
                case "5":
//                    filteredEmployees = new EmployeeFilter().filterById(2, 4, empsToFilter);
//                    optionSelected = true;
                    break;
                case "6":
//                    filteredEmployees = new EmployeeFilter().filterById(2, 4, empsToFilter);
//                    optionSelected = true;
                    break;
                case "7":
//                    filteredEmployees = new EmployeeFilter().filterById(2, 4, empsToFilter);
//                    optionSelected = true;
                    break;

                default:
                    System.out.println("\nNo such submenu, try again (ex: 1)");
                    util.enterAnyValueToContinue();
                    break;
            }

            util.clearScreen();
        }

        return filteredEmployees;
    }

    private EmployeeSortCondition sortMenu() {
        Scanner sc = new Scanner(System.in);
        String nav = "";
        boolean optionSelected = false;

        EmployeeSortCondition condition = null;

        util.clearScreen();

        while (!optionSelected) {
            System.out.println("Sort by");
            System.out.println();
            System.out.println("\t1. salary asc");
            System.out.println("\t2. salary desc");
            System.out.println("\t3. birthdate asc");
            System.out.println("\t4. birthdate desc");
            System.out.println("\t5. engaged_on asc");
            System.out.println("\t6. engaged_on desc");


            System.out.print("\nenter submenu number: ");
            nav = sc.nextLine();

            switch (nav) {
                case "1":
                    condition = EmployeeSortCondition.SALARY_ASC;
                    optionSelected = true;
                    break;
                case "2":
                    condition = EmployeeSortCondition.SALARY_DESC;
                    optionSelected = true;
                    break;
                case "3":
                    condition = EmployeeSortCondition.BIRTHDATE_ASC;
                    optionSelected = true;
                    break;
                case "4":
                    condition = EmployeeSortCondition.BIRTHDATE_DESC;
                    optionSelected = true;
                    break;
                case "5":
                    condition = EmployeeSortCondition.ENGAGEDON_ASC;
                    optionSelected = true;
                    break;
                case "6":
                    condition = EmployeeSortCondition.ENGAGEDON_DESC;
                    optionSelected = true;
                    break;

                default:
                    System.out.println("\nNo such submenu, try again (ex: 1)");
                    util.enterAnyValueToContinue();
                    break;
            }

            util.clearScreen();
        }

        return condition;
    }
}
