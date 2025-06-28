package core;

import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service
public class UserInputService {
    private static final Scanner scanner = new Scanner(System.in);

    public String getValidInput(String prompt, InputValidator validator, String errorMsg) {
        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine().trim();
            if (validator.validate(input)) return input;
            System.out.println("!!! " + errorMsg);
        }
    }

    public double readValidDouble(String prompt, double min, double max) {
        while (true) {
            try {
                System.out.print(prompt + " ");
                double value = Double.parseDouble(scanner.nextLine());
                if (value >= min && value <= max) return value;
                System.out.println("Please enter a number between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    public int readValidInt(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt + " ");
                int value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) return value;
                System.out.println("Please enter a number between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number");
            }
        }
    }
}