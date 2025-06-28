package domain.nutrition;

import domain.products.Product;

public interface DietPlanObserver {
    void onProductAdded(Product product);
}