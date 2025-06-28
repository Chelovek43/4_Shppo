package domain.nutrition;

import domain.person.Person;

public interface CalorieCalculationStrategy {
    double calculateCalories(Person person);
}