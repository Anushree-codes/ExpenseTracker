package service;

import enums.TransactionType;
import model.Transaction;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExpenseTrackerService {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void showMonthlySummary() {
        Map<YearMonth, List<Transaction>> grouped = new TreeMap<>();

        for (Transaction t : transactions) {
            YearMonth ym = YearMonth.from(t.getDate());
            grouped.computeIfAbsent(ym, k -> new ArrayList<>()).add(t);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        for (YearMonth ym : grouped.keySet()) {
            double income = 0, expense = 0;
            for (Transaction t : grouped.get(ym)) {
                if (t.getType() == TransactionType.INCOME) income += t.getAmount();
                else expense += t.getAmount();
            }
            double balance = income - expense;
            System.out.printf("%s -> Income: %.2f | Expense: %.2f | Balance: %.2f%n",
                    ym.format(formatter), income, expense, balance);
        }
    }
    public void clearTransactions() {
        transactions.clear();
    }
}
