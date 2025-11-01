/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milkteashop;

/**
 *
 * @author LENOVO
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import managers.*;
import models.*;

public class MilkTeaShop {
    private static Scanner sc = new Scanner(System.in);
    private static List<Ingredient> ingredients = new ArrayList<>();
    private static List<Beverage> beverages = new ArrayList<>();

    private static final String INGREDIENT_FILE = "ingredients.dat";
    private static final String BEVERAGE_FILE = "beverages.dat";

    public static void main(String[] args) {
        // Tự động đọc dữ liệu khi khởi động
        loadData();
        runMenu();
        // Lưu lại khi thoát
        saveData();
    }

    public static void runMenu() {
        int choice;
        do {
            System.out.println("\n===== MENU QUẢN LÝ TRÀ SỮA =====");
            System.out.println("1. Quản lý Nguyên liệu");
            System.out.println("2. Quản lý Đồ uống");
            System.out.println("3. Bán đồ uống");
            System.out.println("0. Thoát");
            System.out.print("→ Chọn: ");
            choice = getInt();

            switch (choice) {
                case 1: handleManageIngredients();
                case 2: handleManageBeverages();
                case 3: handleSellBeverage();
                case 0: System.out.println("Dữ liệu đã được lưu. Tạm biệt!");
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    // ===================== QUẢN LÝ NGUYÊN LIỆU =====================
    public static void handleManageIngredients() {
        int choice;
        do {
            System.out.println("\n--- MENU NGUYÊN LIỆU ---");
            System.out.println("1. Thêm nguyên liệu");
            System.out.println("2. Xem danh sách nguyên liệu");
            System.out.println("3. Xem nguyên liệu hết hạn");
            System.out.println("0. Quay lại");
            System.out.print("→ Chọn: ");
            choice = getInt();

            switch (choice) {
                case 1: addIngredient();
                case 2: showIngredients();
                case 3: showExpiredIngredients();
                case 0: {}
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private static void addIngredient() {
        System.out.print("Nhập ID: ");
        String id = sc.nextLine();
        System.out.print("Tên: ");
        String name = sc.nextLine();
        System.out.print("Số lượng (kg): ");
        double qty = getDouble();
        System.out.print("Giá nhập/kg: ");
        double cost = getDouble();

        System.out.print("Ngày nhập (yyyy-MM-dd): ");
        Date purchase = parseDate(sc.nextLine());
        System.out.print("Hạn sử dụng (yyyy-MM-dd): ");
        Date expiry = parseDate(sc.nextLine());

        Ingredient ing = new Ingredient(qty, cost, purchase, expiry, id, name);
        ingredients.add(ing);
        System.out.println("Đã thêm nguyên liệu!");
        saveData();
    }

    private static void showIngredients() {
        if (ingredients.isEmpty()) {
            System.out.println("Chưa có nguyên liệu nào!");
            return;
        }
        System.out.println("\n--- DANH SÁCH NGUYÊN LIỆU ---");
        for (Ingredient i : ingredients) {
            System.out.println(i);
        }
    }

    private static void showExpiredIngredients() {
        boolean found = false;
        System.out.println("\n--- NGUYÊN LIỆU HẾT HẠN ---");
        for (Ingredient i : ingredients) {
            if (i.checkExpiryDate()) {
                System.out.println("" + i);
                found = true;
            }
        }
        if (!found) System.out.println("Không có nguyên liệu hết hạn.");
    }

    // ===================== QUẢN LÝ ĐỒ UỐNG =====================
    public static void handleManageBeverages() {
        int choice;
        do {
            System.out.println("\n--- MENU ĐỒ UỐNG ---");
            System.out.println("1. Thêm đồ uống");
            System.out.println("2. Xem danh sách đồ uống");
            System.out.println("0. Quay lại");
            System.out.print("→ Chọn: ");
            choice = getInt();

            switch (choice) {
                case 1: addBeverage();
                case 2: showBeverages();
                case 0: {}
                default:  System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private static void addBeverage() {
        System.out.print("Nhập ID đồ uống: ");
        String id = sc.nextLine();
        System.out.print("Tên đồ uống: ");
        String name = sc.nextLine();
        System.out.print("Giá bán: ");
        double price = getDouble();

        Beverage bev = new Beverage(price, id, name);

        if (ingredients.isEmpty()) {
            System.out.println("⚠ Chưa có nguyên liệu nào để thêm vào đồ uống!");
        } else {
            System.out.println("Chọn nguyên liệu cần thêm (nhập ID, 'x' để dừng):");
            for (Ingredient ing : ingredients) System.out.println(ing);

            while (true) {
                System.out.print("ID nguyên liệu: ");
                String ingID = sc.nextLine();
                if (ingID.equalsIgnoreCase("x")) break;

                Ingredient found = findIngredientByID(ingID);
                if (found == null) {
                    System.out.println("Không tìm thấy ID nguyên liệu!");
                    continue;
                }

                System.out.print("Số lượng cần dùng (kg): ");
                double qty = getDouble();
                bev.addIngredientToRecipe(found, qty);
            }
        }

        beverages.add(bev);
        System.out.println("Đã thêm đồ uống!");
        saveData();
    }

    private static void showBeverages() {
        if (beverages.isEmpty()) {
            System.out.println("Chưa có đồ uống nào!");
            return;
        }
        System.out.println("\n--- DANH SÁCH ĐỒ UỐNG ---");
        for (Beverage b : beverages) {
            System.out.printf("%s | Giá vốn: %.2f | Lợi nhuận: %.2f%n",
                    b, b.calculateCost(), b.calculateProfit());
        }
    }

    // ===================== BÁN ĐỒ UỐNG =====================
    private static void handleSellBeverage() {
        if (beverages.isEmpty()) {
            System.out.println("Chưa có đồ uống nào để bán!");
            return;
        }
        System.out.println("\n--- DANH SÁCH ĐỒ UỐNG ---");
        for (Beverage b : beverages) System.out.println(b);
        System.out.print("Nhập ID đồ uống cần bán: ");
        String id = sc.nextLine();
        Beverage selected = findBeverageByID(id);

        if (selected == null) {
            System.out.println("Không tìm thấy đồ uống!");
            return;
        }

        System.out.print("Số lượng cần bán: ");
        int qty = getInt();

        // Kiểm tra tồn kho
        if (!checkStock(selected, qty)) {
            System.out.println("Không đủ nguyên liệu để bán!");
            return;
        }

        // Trừ nguyên liệu sau khi bán
        deductIngredients(selected, qty);

        double totalProfit = selected.calculateProfit() * qty;
        System.out.printf("Đã bán %d ly %s. Tổng lợi nhuận: %.2f%n",
                qty, selected.getName(), totalProfit);

        saveData();
    }

    // ===================== LOGIC KIỂM TRA TỒN KHO =====================
    private static boolean checkStock(Beverage bev, int quantity) {
        for (Map.Entry<Ingredient, Double> e : bev.getIngredientsNeeded().entrySet()) {
            Ingredient ing = e.getKey();
            double required = e.getValue() * quantity;
            if (ing.getQuantityKg() < required || ing.checkExpiryDate()) {
                return false;
            }
        }
        return true;
    }

    private static void deductIngredients(Beverage bev, int quantity) {
        for (Map.Entry<Ingredient, Double> e : bev.getIngredientsNeeded().entrySet()) {
            Ingredient ing = e.getKey();
            double used = e.getValue() * quantity;
            ing.setQuantityKg(ing.getQuantityKg() - used);
        }
    }

    // ===================== FILE I/O =====================
    private static void saveData() {
        try (ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(INGREDIENT_FILE));
             ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(BEVERAGE_FILE))) {
            oos1.writeObject(ingredients);
            oos2.writeObject(beverages);
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu dữ liệu: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadData() {
        try (ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(INGREDIENT_FILE))) {
            ingredients = (List<Ingredient>) ois1.readObject();
            System.out.println("Đã tải danh sách nguyên liệu.");
        } catch (Exception e) {
            ingredients = new ArrayList<>();
        }

        try (ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(BEVERAGE_FILE))) {
            beverages = (List<Beverage>) ois2.readObject();
            System.out.println("Đã tải danh sách đồ uống.");
        } catch (Exception e) {
            beverages = new ArrayList<>();
        }
    }

    // ===================== HÀM HỖ TRỢ =====================
    private static int getInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Nhập lại (số nguyên): ");
            }
        }
    }

    private static double getDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Nhập lại (số thực): ");
            }
        }
    }

    private static Date parseDate(String input) {
        try {
            return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(input);
        } catch (Exception e) {
            System.out.println("Sai định dạng, dùng ngày hiện tại thay thế.");
            return new Date();
        }
    }

    private static Ingredient findIngredientByID(String id) {
        for (Ingredient i : ingredients)
            if (i.getProductID().equalsIgnoreCase(id))
                return i;
        return null;
    }

    private static Beverage findBeverageByID(String id) {
        for (Beverage b : beverages)
            if (b.getProductID().equalsIgnoreCase(id))
                return b;
        return null;
    }
}

