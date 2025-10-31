/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author LENOVO
 * Lớp cha trừu tượng (Abstract Class) cho tất cả sản phẩm.
 * Bắt buộc phải "implements Serializable"
 * Đây là yêu cầu kỹ thuật để Người 4 (phụ trách I/O) 
 * có thể dùng ObjectOutputStream để lưu các đối tượng (như Ingredient, Beverage)
 * xuống file.
 */
import java.io.Serializable;

public abstract class BaseProduct implements Serializable{
    protected String productID;
    protected String name;

    public BaseProduct(String productID, String name) {
        this.productID = productID;
        this.name = name;
    }

    public String getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }
    
    public abstract double calculateCost();
}
