/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Package for the model class and other supporting classes used for data
 * handling and manipulation inside the application in Model View Controller achitecture .
 */
package pl.polsl.model;

/**
 ***
 * Represents the person in the class.
 *
 * @author Bartosz Jalowiecki
 * @version 3.1-SNAPSHOT
 */
public class Person {

    /**
     * Field representing work year
     */
    //private String Name;
    private int workYear;

    /**
     * Field representing experience level
     */
    //private String Surname;
    private String experienceLevel;
    /**
     * Field representing employment type
     */
    private String employmentType;//{FullTime, PartTime, Temporary, Seasonal, Other};
    /**
     * Field representing job title
     */
    private String jobTitle;

    /**
     * Field representing salary
     */
    private int salary;
    /**
     * Field representing salary currency
     */
    private String salaryCurrency;

    /**
     * Field representing salary in USD
     */
    private int salaryInUSD;

    /**
     * Field representing employee residence country
     */
    private String employeeResidence;

    /**
     * Field representing ratio of remote work
     */
    private int remoteRatio;

    /**
     * Field representing country of company location
     */
    private String companyLocation;

    /**
     * Field representing company size
     */
    private String companySize;

    /**
     * Constructior, that creates an object of class Person.
     *
     * @param workY employee year of work
     * @param expLevel employee level of experience
     * @param empTy employee type of employemnt
     * @param jTitle employee job title
     * @param sal employee salary
     * @param salCur currency of employee salary
     * @param salUSD employee salary in US Dollars
     * @param emplRes employee country of residence
     * @param remRatio ratio of employee remote work
     * @param compLoc employee company location
     * @param compSize employee company size
     */
    public Person(int workY, String expLevel, String empTy, String jTitle, int sal, String salCur, int salUSD, String emplRes, int remRatio, String compLoc, String compSize)
            throws IllegalArgumentException {
        this.workYear = workY;
        this.experienceLevel = expLevel;
        this.employmentType = empTy;//{FullTime, PartTime, Temporary, Seasonal, Other};
        this.jobTitle = jTitle;
        this.salary = sal;
        this.salaryCurrency = salCur;
        this.salaryInUSD = salUSD;
        this.employeeResidence = emplRes;
        this.remoteRatio = remRatio;
        this.companyLocation = compLoc;
        this.companySize = compSize;
    }

    /**
     * Accessor to employee job title
     *
     * @return employee job title
     */
    String getJobTitle() {
        return jobTitle;
    }

    /**
     * Accessor to employee salary
     *
     * @return employee salary
     */
    int getSalary() {
        return salary;
    }

    /**
     * Accessor to salary in US Dollars
     *
     * @return employee salary in US Dollars
     */
    int getSalaryUSD() {
        return salaryInUSD;
    }

    /**
     * Accessor to employee salary currency
     *
     * @return employee salary currency
     */
    String getCurrency() {
        return salaryCurrency;
    }

    /**
     * Accessor to employee residence country
     *
     * @return employee residence country
     */
    String getEmplRes() {
        return employeeResidence;
    }

    /**
     * Accessor to employee type of employment
     *
     * @return employee type of employment
     */
    String getTypeOfEmpl() {
        return employmentType;
    }

    /**
     * Accessor to country of employee company location
     *
     * @return country of employee company location
     */
    String getCompLoc() {
        return companyLocation;
    }

    /**
     * Accessor to employee ratio of remote work
     *
     * @return employee ratio of remote work
     */
    int getRemoteRatio() {
        return remoteRatio;
    }
}
