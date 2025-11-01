package manager;

import Interfaces.IManagement;
import models.Ingredient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientManager implements IManagement<Ingredient> {
    private List<Ingredient> ingredients = new ArrayList<>();

    @Override
    public void add(Ingredient obj) {
        ingredients.add(obj);
    }

    @Override
    public void remove(String id) {
        ingredients.removeIf(i -> i.getProductID().equals(id));
    }

    @Override
    public boolean update(String id, Ingredient newobj) {
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getProductID().equals(id)) {
                ingredients.set(i, newobj);
                return true;
            }
        }
        return false;
    }

    @Override
    public Ingredient find(String id) {
        for (Ingredient i : ingredients) {
            if (i.getProductID().equals(id)) return i;
        }
        return null;
    }

    @Override
    public void displayAll() {
        for (Ingredient i : ingredients) {
            System.out.println(i);
        }
    }

    @Override
    public void saveData(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(ingredients);
        }
    }

    @Override
    public void readData(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            ingredients = (List<Ingredient>) ois.readObject();
        }
    }
}
