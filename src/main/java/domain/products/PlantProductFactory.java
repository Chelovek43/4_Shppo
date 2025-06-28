package domain.products;

import org.springframework.stereotype.Component;

@Component("plantFactory")
public class PlantProductFactory implements ProductFactory {
    @Override
    public Product createProduct(String name, double calories, double protein, double fat, double carbs) {
        return new PlantProduct(name, calories, protein, fat, carbs);
    }
}