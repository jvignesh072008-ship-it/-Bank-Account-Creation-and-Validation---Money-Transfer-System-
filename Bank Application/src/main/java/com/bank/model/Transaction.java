package com.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderAccountNumber;
    private String receiverAccountNumber;
    private double transferAmount;
    private double remainingBalance;
    private LocalDateTime transactionDate;

    protected Transaction() {
    }

    public Transaction(String senderAccountNumber, String receiverAccountNumber, double transferAmount, double remainingBalance) {
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.transferAmount = transferAmount;
        this.remainingBalance = remainingBalance;
        this.transactionDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void displayTransactionDetails() {
        System.out.println("Transaction successful");
        System.out.println("Sender Account   : " + senderAccountNumber);
        System.out.println("Receiver Account : " + receiverAccountNumber);
        System.out.println("Transfer Amount  : ₹" + transferAmount);
        System.out.println("Remaining Balance: ₹" + remainingBalance);
    }
}
