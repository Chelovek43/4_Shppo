package core;

import domain.diet.CompositeDietCycle;
import domain.diet.DietCycle;
import domain.diet.SingleDietCycle;
import domain.products.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DietCycleService {
    private static final double CARB_THRESHOLD = 15.0;

    public CompositeDietCycle createDietCycle(List<Product> availableProducts) {
        List<Product> lowCarbProducts = new ArrayList<>();
        List<Product> highCarbProducts = new ArrayList<>();

        for (Product p : availableProducts) {
            if (p.getCarbs() < CARB_THRESHOLD) {
                lowCarbProducts.add(p);
            } else {
                highCarbProducts.add(p);
            }
        }

        DietCycle lowCarbCycle = new SingleDietCycle(
                String.format("Low (5 days), need 150g, eat: %s",
                        formatProductList(lowCarbProducts)),
                5);

        DietCycle highCarbCycle = new SingleDietCycle(
                String.format("High (2 days), need 350g, a lot in: %s",
                        formatProductList(highCarbProducts)),
                2);

        CompositeDietCycle fullCycle = new CompositeDietCycle();
        fullCycle.add(lowCarbCycle);
        fullCycle.add(highCarbCycle);
        return fullCycle;
    }

    private String formatProductList(List<Product> products) {
        StringBuilder sb = new StringBuilder();
        for (Product p : products) {
            sb.append(String.format("%s (%.1fг), ", p.getName(), p.getCarbs()));
        }
        return !sb.isEmpty() ? sb.substring(0, sb.length() - 2) : "";
    }
}