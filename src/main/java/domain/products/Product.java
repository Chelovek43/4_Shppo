package domain.products;

public abstract class Product {
    private String name;
    private double calories;
    private double protein;
    private double fat;
    private double carbs;

    public Product(String name, double calories, double protein, double fat, double carbs) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    // Геттеры
    public String getName() { return name; }
    public double getCalories() { return calories; }
    public double getProtein() { return protein; }
    public double getFat() { return fat; }
    public double getCarbs() { return carbs; }

    @Override
    public String toString() {
        return String.format("%s (%.1f ccal, P:%.1fg, F:%.1fg, C:%.1fg)",
                name, calories, protein, fat, carbs);
    }
}