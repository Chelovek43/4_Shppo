package domain.diet;

import domain.products.Product;
import domain.nutrition.DietPlanObserver;


import java.util.ArrayList;
import java.util.List;

public class ObservableDietPlan extends DietPlan {
    private List<DietPlanObserver> observers = new ArrayList<>();

    protected ObservableDietPlan(List<Product> products, int durationDays) {
        super(products, durationDays);
    }

    public static ObservableDietPlan createFrom(DietPlan plan) {
        return new ObservableDietPlan(plan.getProducts(), plan.getDurationDays());
    }

    public void addObserver(DietPlanObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(DietPlanObserver observer) {
        observers.remove(observer);
    }

    public void addProductWithNotification(Product product) {
        getProducts().add(product);
        notifyObservers(product);
    }

    private void notifyObservers(Product product) {
        for (DietPlanObserver observer : observers) {
            observer.onProductAdded(product);
        }
    }
}