package domain.products;

import org.springframework.stereotype.Component;

@Component("animalFactory")
public class AnimalProductFactory implements ProductFactory {
    @Override
    public Product createProduct(String name, double calories, double protein, double fat, double carbs) {
        return new AnimalProduct(name, calories, protein, fat, carbs);
    }
}