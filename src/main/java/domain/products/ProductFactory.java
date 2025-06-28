package domain.products;

public interface ProductFactory {
    Product createProduct(String name, double calories, double protein, double fat, double carbs);
}