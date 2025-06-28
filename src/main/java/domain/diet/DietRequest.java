package domain.diet;

import domain.products.Product;
import java.util.List;

public class DietRequest {
    private String name;
    private double weight;
    private double height;
    private int age;
    private List<Product> selectedProducts;

    public DietRequest(String name, double weight, double height, int age, List<Product> products) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.selectedProducts = products;
    }

    // Геттеры
    public String getName() { return name; }
    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public int getAge() { return age; }
    public List<Product> getSelectedProducts() { return selectedProducts; }
}