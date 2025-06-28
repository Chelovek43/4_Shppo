package domain.nutrition;

import domain.products.Product;

public class LoggingObserver implements DietPlanObserver {
    @Override
    public void onProductAdded(Product product) {
        System.out.printf("[OBSERVER] Add prod: %s (%.1f kkal)\n",
                product.getName(), product.getCalories());
    }
}