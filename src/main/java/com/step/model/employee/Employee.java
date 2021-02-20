package com.step.model.employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Employee {
    /*
    int id - unique, gestionated from data base
	string name
	string surname
    string idnp - unique, gestionated from app
    LocalDate birthDate
    LocalDate engagedOn
    double salary
    Job job - Job is a enum with jobs
     */

//    employee fields
    private Integer id;
    private String name;
    private String surname;
    private String idnp;
    private LocalDate birthDate;
    private LocalDate engagedOn;
    private Double salary;
    private Job job;

//    addition fields
    private static int lastId = 0;
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Employee(String name, String surname, String idnp, LocalDate birthDate, Double salary, Job job) {
        this.id = lastId++;
        this.name = name;
        this.surname = surname;
        this.idnp = idnp;
        this.birthDate = birthDate;
        this.engagedOn = LocalDate.now();
        this.salary = salary;
        this.job = job;
    }

    /**
     * Constructor, used for editing employees, only fields that need to be edited
     *
     * @param name new name
     * @param surname new surname
     * @param salary new salary
     * @param job new job
     */
    public Employee(String name, String surname, Double salary, Job job) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getIdnp() {
        return idnp;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getBirthDateFormatted() {
        return birthDate.format(dateTimeFormatter);
    }

    public LocalDate getEngagedOn() {
        return engagedOn;
    }

    public String getEngagedOnFormatted() {
        return engagedOn.format(dateTimeFormatter);
    }

    public Double getSalary() {
        return salary;
    }

    public Job getJob() {
        return job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEngagedOn(LocalDate engagedOn) {
        this.engagedOn = engagedOn;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public static List<Employee> getDummyEmployees () {
        List<Employee> emps = new ArrayList<>(5);

        emps.add(new Employee("Victor", "Dulap", "1234567890123", LocalDate.of(2003,1,10), (double) 2000, Job.PROGRAMMER));
        emps.add(new Employee("Ion", "Alb", "4567112238903", LocalDate.of(2000,12,12), (double) 1200, Job.CHEF));
        emps.add(new Employee("Ion", "Alb", "2412356718903", LocalDate.of(1989,1,29), (double) 900, Job.BOSS));
        emps.add(new Employee("Alexei", "Ceban", "0152237346189", LocalDate.of(1999,10,3), (double) 600, Job.ENGINEER));
        emps.add(new Employee("Maria", "Cretu", "2341251673890", LocalDate.of(1989,2,22), (double) 700, Job.CLEANER));
        emps.add(new Employee("Ana", "Donii", "0123367451289", LocalDate.of(1996,9,13), (double) 800, Job.MANAGER));

        return emps;
    }

    protected static void resetId() {
        lastId = 0;
    }
}
