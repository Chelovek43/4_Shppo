package domain.nutrition;

import domain.products.Product;

public class NutritionTrackerObserver implements DietPlanObserver {
    private double totalCalories = 0;

    @Override
    public void onProductAdded(Product product) {
        totalCalories += product.getCalories();
        System.out.printf("[TRACKER] Now sum kkal: %.1f\n", totalCalories);
    }
}