package domain.person;

public class SlowMetabolismDecorator extends PersonDecorator {
    private double metabolicFactor;

    public SlowMetabolismDecorator(Person person, double metabolicFactor) {
        super(person);
        this.metabolicFactor = metabolicFactor;
    }

    @Override
    public double getMetabolicRate() {
        return metabolicFactor;
    }

    @Override
    public String toString() {
        return person.toString() + String.format(", metabolism: x%.1f", metabolicFactor);
    }
}