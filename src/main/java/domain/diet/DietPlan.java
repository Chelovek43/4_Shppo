package domain.diet;

import domain.products.Product;
import java.util.ArrayList;
import java.util.List;

public class DietPlan {
    private List<Product> products;
    private int durationDays;

    protected DietPlan(List<Product> products, int durationDays) {
        this.products = new ArrayList<>(products);
        this.durationDays = durationDays;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public int getDurationDays() {
        return durationDays;
    }

    public String getTotalNutrition() {
        double totalCalories = 0, totalProtein = 0, totalFat = 0, totalCarbs = 0;
        for (Product p : products) {
            totalCalories += p.getCalories();
            totalProtein += p.getProtein();
            totalFat += p.getFat();
            totalCarbs += p.getCarbs();
        }
        return String.format("Itogo: %.1f kkal, B: %.1fг, F: %.1fг, C: %.1fг",
                totalCalories, totalProtein, totalFat, totalCarbs);
    }

    public static class Builder {
        private final List<Product> products = new ArrayList<>();
        private int durationDays = 7;

        public Builder addProduct(Product product) {
            this.products.add(product);
            return this;
        }

        public Builder setDuration(int durationDays) {
            this.durationDays = durationDays;
            return this;
        }

        public DietPlan build() {
            if (products.isEmpty()) {
                throw new IllegalStateException("Not empty!");
            }
            return new DietPlan(products, durationDays);
        }
    }
}