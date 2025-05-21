import enums.TransactionType;
import model.Transaction;
import service.ExpenseTrackerService;
import utils.FileHandler;
import utils.InputHelper;

import java.time.LocalDate;
import java.util.*;

public class ExpenseTracker {
    public static void main(String[] args) {
        ExpenseTrackerService service = new ExpenseTrackerService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Entry\n2. View Summary\n3. Load From File\n4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    addTransaction(sc, service);
                    break;
                case 2:
                    service.showMonthlySummary();
                    break;
                case 3:
                    System.out.print("Enter file path: ");
                    String filePath = sc.nextLine();
                    FileHandler.loadFromFile(filePath, service);
                    service.showMonthlySummary();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    private static void addTransaction(Scanner sc, ExpenseTrackerService service) {
        TransactionType type = InputHelper.getTransactionType(sc);
        Set<String> validCategories = (type == TransactionType.EXPENSE)
                ? Set.of("Food", "Rent", "Travel")
                : Set.of("Salary", "Business");

        String category = InputHelper.getValidCategory(sc, validCategories);
        double amount = InputHelper.getDoubleInput(sc, "Enter amount: ");
        LocalDate date = InputHelper.getDateInput(sc, "Enter date (yyyy-mm-dd): ");

        Transaction t = new Transaction(type, category, amount, date);
        service.addTransaction(t);
        System.out.println("Transaction added.");
    }
}