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
public class OfficialEmployee extends Employee{
    private double monthlySalary;

    public OfficialEmployee(double monthlySalary, String employeeID, String fullName, double hoursWorked) {
        super(employeeID, fullName, hoursWorked);
        this.monthlySalary = monthlySalary;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
    
    @Override
    public double calculateSalary(){
        return monthlySalary;
    }
    
    @Override
    public String toString() {
        String formatString = "OfficialEmployee [ID=%s, Tên=%s, Giờ làm=%.1f, Lương=%.2f]";
        
        return String.format(formatString,
                this.employeeID,
                this.fullName,
                this.hoursWorked,
                this.calculateSalary()); // Tự động gọi hàm calculateSalary()
    }
}
