/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author LENOVO
 * Quản lý chung cho dự án
 * <T> là ký hiệu cho Generic Type
 */
import java.io.IOException;

public interface IManagement<T>{
    void add(T obj);
    
    void remove(String id);
            
    boolean update(String id, T newobj);
    
    T find(String id);
    
    void displayAll();
    
    void saveData(String fileName) throws IOException;
    
    void readData(String fileName) throws IOException, ClassNotFoundException;
}
