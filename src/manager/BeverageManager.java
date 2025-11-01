package manager;

import Interfaces.IManagement;
import models.Beverage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BeverageManager implements IManagement<Beverage> {
    private List<Beverage> beverages = new ArrayList<>();

    @Override
    public void add(Beverage obj) {
        beverages.add(obj);
    }

    @Override
    public void remove(String id) {
        beverages.removeIf(b -> b.getProductID().equals(id));
    }

    @Override
    public boolean update(String id, Beverage newobj) {
        for (int i = 0; i < beverages.size(); i++) {
            if (beverages.get(i).getProductID().equals(id)) {
                beverages.set(i, newobj);
                return true;
            }
        }
        return false;
    }

    @Override
    public Beverage find(String id) {
        for (Beverage b : beverages) {
            if (b.getProductID().equals(id)) return b;
        }
        return null;
    }

    @Override
    public void displayAll() {
        for (Beverage b : beverages) {
            System.out.println(b);
        }
    }

    @Override
    public void saveData(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(beverages);
        }
    }

    @Override
    public void readData(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            beverages = (List<Beverage>) ois.readObject();
        }
    }
}
