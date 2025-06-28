package domain.nutrition;

import domain.person.Person;

public class CalorieCalculator {
    private CalorieCalculationStrategy strategy;

    public CalorieCalculator(CalorieCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(CalorieCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculate(Person person) {
        return strategy.calculateCalories(person);
    }
}