package utils;

import enums.TransactionType;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;

public class InputHelper {
    public static TransactionType getTransactionType(Scanner sc) {
        System.out.print("Enter type (INCOME/EXPENSE): ");
        return TransactionType.valueOf(sc.nextLine().trim().toUpperCase());
    }

    public static String getValidCategory(Scanner sc, Set<String> validCategories) {
        while (true) {
            System.out.print("Enter category " + validCategories + ": ");
            String input = sc.nextLine().trim();
            if (validCategories.contains(input)) {
                return input;
            }
            System.out.println("Invalid category. Try again.");
        }
    }

    public static double getDoubleInput(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double amount = Double.parseDouble(sc.nextLine().trim());
                if (amount >= 0) return amount;
                System.out.println("Amount must be positive.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    public static LocalDate getDateInput(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid date format. Use yyyy-mm-dd.");
            }
        }
    }
}
