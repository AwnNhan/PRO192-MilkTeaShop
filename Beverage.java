package models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
import java.util.Map;
import java.util.HashMap;

public class Beverage extends BaseProduct{
    private double sellPrice;
    private Map<Ingredient, Double> ingredientsNeeded;

    public Beverage(double sellPrice, String productID, String name) {
        super(productID, name);
        this.sellPrice = sellPrice;
        
        this.ingredientsNeeded = new HashMap<>();
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }
    
    public double getSellPrice() {
        return sellPrice;
    }

    public Map<Ingredient, Double> getIngredientsNeeded() {
        return ingredientsNeeded;
    }

    
    @Override
    public double calculateCost() {
        double totalCost = 0;
        for (Map.Entry<Ingredient, Double> entry : ingredientsNeeded.entrySet()){
            Ingredient ingredient = entry.getKey();
            Double quantityNeeded = entry.getValue();
            
            totalCost += ingredient.getUnitCost() * quantityNeeded;
        }
        
        return totalCost;
    }
    
    public void addIngredientToRecipe(Ingredient ingredient, double quantity){
        this.ingredientsNeeded.put(ingredient, quantity);
    }
    
    public double calculateProfit(){
        return this.sellPrice - this.calculateCost();
    }

    @Override
    public String toString() {
        String formatString = "Beverage [ID = %s, Tên = %s, Giá bán = %.2f]";
        return String.format(formatString,
                            this.productID,
                            this.name,
                            this.sellPrice);
    }
    
    
}
