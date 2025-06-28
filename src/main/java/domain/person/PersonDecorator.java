package domain.person;

public abstract class PersonDecorator extends Person {
    protected Person person;

    public PersonDecorator(Person person) {
        super(person.getWeight(), person.getHeight(), person.getAge());
        this.person = person;
    }

    public abstract double getMetabolicRate();
}