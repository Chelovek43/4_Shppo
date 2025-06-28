package core;

import domain.diet.DietPlan;
import domain.diet.CompositeDietCycle;
import domain.person.Person;
import domain.person.SlowMetabolismDecorator;
import domain.products.Product;
import domain.products.ProductFactory;
import domain.nutrition.LoggingObserver;
import domain.nutrition.NutritionTrackerObserver;
import domain.diet.ObservableDietPlan;
import domain.nutrition.CalorieCalculator;
import domain.nutrition.HarrisBenedictStrategy;
import domain.nutrition.MifflinStJeorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInputHandler {
    private final UserInputService inputService;
    private final ProductSelectionService productSelectionService;
    private final DietCycleService dietCycleService;
    private final FileStorageService fileStorageService;
    private final ProductFactory plantFactory;
    private final ProductFactory animalFactory;

    @Autowired
    public UserInputHandler(
            UserInputService inputService,
            ProductSelectionService productSelectionService,
            DietCycleService dietCycleService,
            FileStorageService fileStorageService,
            @Qualifier("plantFactory") ProductFactory plantFactory,
            @Qualifier("animalFactory") ProductFactory animalFactory) {
        this.inputService = inputService;
        this.productSelectionService = productSelectionService;
        this.dietCycleService = dietCycleService;
        this.fileStorageService = fileStorageService;
        this.plantFactory = plantFactory;
        this.animalFactory = animalFactory;
    }

    public void start() {
        System.out.println("=== Nutrition Plan Constructor ===");

        while (true) {
            try {
                String name = inputService.getValidInput(
                        "Enter name (or 'exit' to quit):",
                        input -> !input.isEmpty(),
                        "Name cannot be empty");

                if (name.equalsIgnoreCase("exit")) break;

                double weight = inputService.readValidDouble("Weight (kg):", 30, 120);
                double height = inputService.readValidDouble("Height (cm):", 100, 250);
                int age = inputService.readValidInt("Age:", 1, 120);
                double metabolism = inputService.readValidDouble("Metabolism coefficient (0.8-1.2):", 0.8, 1.2);

                List<Product> selectedProducts = productSelectionService.selectProducts();

                DietPlan.Builder builder = new DietPlan.Builder();
                for (Product product : selectedProducts) {
                    builder.addProduct(product);
                }
                DietPlan plan = builder.setDuration(7).build();

                ObservableDietPlan observablePlan = ObservableDietPlan.createFrom(plan);
                observablePlan.addObserver(new LoggingObserver());
                observablePlan.addObserver(new NutritionTrackerObserver());

                Person person = new Person(weight, height, age);
                person = new SlowMetabolismDecorator(person, metabolism);

                CalorieCalculator calculator = new CalorieCalculator(new HarrisBenedictStrategy());
                double harrisCalories = calculator.calculate(person);
                calculator.setStrategy(new MifflinStJeorStrategy());
                double mifflinCalories = calculator.calculate(person);

                CompositeDietCycle fullCycle = dietCycleService.createDietCycle(productSelectionService.getAvailableProducts());

                fileStorageService.saveFullData(name, weight, height, age, metabolism,
                        selectedProducts, plan, person,
                        harrisCalories, mifflinCalories, fullCycle);

                System.out.println("\n✓ All data saved to nutrition_diary.txt");

            } catch (Exception e) {
                System.out.println("⚠ Error: " + e.getMessage());
            }
        }
    }
}