package models;


import models.Employee;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class TrialEmployee extends Employee{
    private double hourlyWage;

    public TrialEmployee(double hourlyWage, String employeeID, String fullName, double hoursWorked) {
        super(employeeID, fullName, hoursWorked);
        this.hourlyWage = hourlyWage;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
    
    public double calculateSalary(){
        return hourlyWage*hoursWorked;
    }

    @Override
    public String toString() {
        String formatString = "TrialEmployee [ID=%s, Tên=%s, Giờ làm=%.1f, Lương=%.2f]";
        
        return String.format(formatString,
                this.employeeID,
                this.fullName,
                this.hoursWorked,
                this.calculateSalary()); 
    }
}
