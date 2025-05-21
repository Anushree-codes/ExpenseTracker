package utils;

import model.Transaction;
import service.ExpenseTrackerService;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileHandler {
    public static void loadFromFile(String filePath, ExpenseTrackerService service) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            service.clearTransactions(); //Clear existing data
            while ((line = br.readLine()) != null) {
                Transaction t = Transaction.fromString(line);
                service.addTransaction(t);
            }
            System.out.println("File loaded successfully.");
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
