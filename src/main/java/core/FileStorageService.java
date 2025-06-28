package core;

import domain.diet.DietPlan;
import domain.diet.CompositeDietCycle;
import domain.diet.DietRequest;
import domain.person.Person;
import domain.products.Product;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class FileStorageService {
    private static final String FILE_PATH = "nutrition_diary.txt";
    private static final String SEPARATOR = "=".repeat(50);

    public void saveFullData(String name, double weight, double height, int age,
                             double metabolism, List<Product> products,
                             DietPlan plan, Person person,
                             double harrisCalories, double mifflinCalories,
                             CompositeDietCycle cycle) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(SEPARATOR + "\n");
            writer.write(name + "\n");

            for (Product p : products) {
                writer.write(String.format("%s: %.1f kcal, P: %.1fg, F: %.1fg, C: %.1fg\n",
                        p.getName(), p.getCalories(), p.getProtein(), p.getFat(), p.getCarbs()));
            }

            writer.write(String.format("%.0f %.0f %d %.1f\n", weight, height, age, metabolism));

            writer.write("=== Generated Nutrition Plan ===\n");
            writer.write(plan.getTotalNutrition() + "\n");

            writer.write("=== Person Characteristics ===\n");
            writer.write(person.toString() + "\n");

            writer.write("=== Calorie Calculation (Harris-Benedict) ===\n");
            writer.write(String.format("Daily norm: %.1f kcal/day\n", harrisCalories));
            writer.write("\n=== Calorie Calculation (Mifflin-St Jeor) ===\n");
            writer.write(String.format("Daily norm: %.1f kcal/day\n", mifflinCalories));

            double calories = mifflinCalories;
            double protein = calories * 0.13 / 4;
            double fat = calories * 0.30 / 9;
            double carbs = calories * 0.57 / 4;

            writer.write("\n=== Recommended Macronutrient Distribution ===\n");
            writer.write(String.format("Protein: %.1fg (13%% of calories)\n", protein));
            writer.write(String.format("Fat: %.1fg (30%% of calories)\n", fat));
            writer.write(String.format("Carbs: %.1fg (57%% of calories)\n", carbs));

            writer.write("\n=== Recommended Nutrition Cycles ===\n");
            writer.write(cycle.getDescription() + "\n");
            writer.write("Total duration: " + cycle.getDuration() + " days\n");
        }
    }

    public synchronized void savePlan(DietRequest request) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write("Request from: " + request.getName() + "\n");
        }
    }
}