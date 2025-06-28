package domain.nutrition;

import domain.person.Person;

public class HarrisBenedictStrategy implements CalorieCalculationStrategy {
    @Override
    public double calculateCalories(Person person) {
        return 88.362 + (13.397 * person.getWeight())
                + (4.799 * person.getHeight())
                - (5.677 * person.getAge());
    }
}