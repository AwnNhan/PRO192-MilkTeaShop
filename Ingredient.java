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

import java.util.Date;

public class Ingredient extends BaseProduct{
    private double quantityKg;
    private double unitCost;
    private Date purchaseDate;
    private Date expiryDate;

    public Ingredient(double quantityKg, double unitCost, Date purchaseDate, Date expiryDate, String productID, String name) {
        super(productID, name);
        this.quantityKg = quantityKg;
        this.unitCost = unitCost;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
    }

    public double getQuantityKg() {
        return quantityKg;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setQuantityKg(double quantityKg) {
        this.quantityKg = quantityKg;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public double calculateCost() {
        return this.quantityKg * this.unitCost;
    }   
    
    public boolean checkExpiryDate(){
        return this.expiryDate.before(new Date());
    }

    @Override
    public String toString() {
        String formatString = "Ingredient [iD = %s, Tên = %s, Số lượng = %.2fkg, Cost/kg = %.2f, Ngày nhập = %s, HSD = %s]";
        return String.format(formatString, 
                            this.productID,
                            this.name,
                            this.quantityKg,
                            this.unitCost,
                            this.purchaseDate,
                            this.expiryDate);
    }
    
}
