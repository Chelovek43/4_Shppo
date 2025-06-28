package core;

@FunctionalInterface
public interface InputValidator {
    boolean validate(String input);
}