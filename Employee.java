/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author LENOVO
 */
import java.io.Serializable;

public abstract class Employee implements Serializable{
     protected String employeeID;
     protected String fullName;
     protected double hoursWorked;

    public Employee(String employeeID, String fullName, double hoursWorked) {
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.hoursWorked = hoursWorked;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getFullName() {
        return fullName;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
     
    public abstract double calculateSalary();
}
