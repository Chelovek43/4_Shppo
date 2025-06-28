package core;

import domain.products.Product;
import domain.products.ProductFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductSelectionService {
    private final Scanner scanner = new Scanner(System.in);
    private final ProductFactory plantFactory;
    private final ProductFactory animalFactory;
    private final List<Product> availableProducts;

    @Autowired
    public ProductSelectionService(
            @Qualifier("plantFactory") ProductFactory plantFactory,
            @Qualifier("animalFactory") ProductFactory animalFactory) {
        this.plantFactory = plantFactory;
        this.animalFactory = animalFactory;

        // Здесь происходят вызовы createProduct() (Сработает AOP)
        this.availableProducts = Arrays.asList(
                plantFactory.createProduct("Apple", 52, 0.3, 0.2, 14),
                plantFactory.createProduct("Banana", 89, 1.1, 0.3, 22.8),
                plantFactory.createProduct("Oatmeal", 68, 2.4, 1.4, 12.0),
                plantFactory.createProduct("Almonds", 579, 21.2, 49.9, 21.6),
                plantFactory.createProduct("Broccoli", 34, 2.8, 0.4, 6.6),
                animalFactory.createProduct("Chicken", 165, 31, 3.6, 0),
                animalFactory.createProduct("Salmon", 208, 20.0, 13.0, 0.0),
                animalFactory.createProduct("Eggs", 143, 12.6, 9.9, 0.7),
                animalFactory.createProduct("Greek Yogurt", 59, 10.0, 0.4, 3.6),
                animalFactory.createProduct("Beef", 250, 26.0, 15.0, 0.0),
                animalFactory.createProduct("Cheddar Cheese", 402, 25.0, 33.0, 1.3)
        );
    }

    public List<Product> selectProducts() {
        List<Product> selected = new ArrayList<>();
        System.out.println("\nAvailable products:");
        for (int i = 0; i < availableProducts.size(); i++) {
            System.out.printf("%2d. %s\n", i + 1, availableProducts.get(i));
        }

        while (true) {
            try {
                System.out.print("\nSelect product (1-" + availableProducts.size() + "), 0 - finish: ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 0) break;
                if (choice < 0 || choice > availableProducts.size()) {
                    System.out.println("Invalid product number");
                    continue;
                }

                Product product = availableProducts.get(choice - 1);
                selected.add(product);
                System.out.println("Added: " + product.getName());

            } catch (NumberFormatException e) {
                System.out.println("Please enter product number");
            }
        }
        return selected;
    }

    public List<Product> selectRandomProducts() {
        Collections.shuffle(availableProducts);
        return new ArrayList<>(availableProducts.subList(0, 3)); // 3 random products
    }



    public List<Product> getAvailableProducts() {
        return this.availableProducts;
    }

    public ProductFactory getPlantFactory() {
        return plantFactory;
    }

    public ProductFactory getAnimalFactory() {
        return animalFactory;
    }


}