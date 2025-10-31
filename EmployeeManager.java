/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

/**
 *
 * @author MSI
 */
import Interfaces.IManagement;
import models.Employee;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager implements IManagement<Employee> {
    private List<Employee> employees = new ArrayList<>();

    @Override
    public void add(Employee obj) {
        employees.add(obj);
    }

    @Override
    public void remove(String id) {
        employees.removeIf(e -> e.getEmployeeID().equals(id));
    }

    @Override
    public boolean update(String id, Employee newobj) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeID().equals(id)) {
                employees.set(i, newobj);
                return true;
            }
        }
        return false;
    }

    @Override
    public Employee find(String id) {
        for (Employee e : employees) {
            if (e.getEmployeeID().equals(id)) return e;
        }
        return null;
    }

    @Override
    public void displayAll() {
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    @Override
    public void saveData(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(employees);
        }
    }

    @Override
    public void readData(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            employees = (List<Employee>) ois.readObject();
        }
    }
}
