package model;

import enums.TransactionType;

import java.time.LocalDate;

public class Transaction {
    private TransactionType type;
    private String category;
    private double amount;
    private LocalDate date;

    public Transaction(TransactionType type, String category,double amount, LocalDate date) {
        this.type = type;
        this.category = category;
        this.date = date;
        this.amount = amount;
    }
    //getters
    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
    @Override
    public String toString() {
        return type + "," + category + "," + amount + "," + date;
    }

    public static Transaction fromString(String line) {
        String[] parts = line.split(",");
        return new Transaction(
                TransactionType.valueOf(parts[0]),
                parts[1],
                Double.parseDouble(parts[2]),
                LocalDate.parse(parts[3])
        );
    }

}
