package com.bank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class BankAccount {

    @Id
    @Column(name = "account_number")
    private String accountNumber;

    private String customerName;
    private String mobileNumber;
    private String email;
    private double initialDeposit;
    private String accountType;
    private double balance;

    protected BankAccount() {
    }

    public BankAccount(String accountNumber, String customerName, String mobileNumber, String email, double initialDeposit, String accountType) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.initialDeposit = initialDeposit;
        this.accountType = accountType;
        this.balance = initialDeposit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public double getInitialDeposit() {
        return initialDeposit;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void debit(double amount) {
        this.balance -= amount;
    }

    public void credit(double amount) {
        this.balance += amount;
    }

    public void displayAccountDetails() {
        System.out.println("Account created successfully");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Customer Name  : " + customerName);
        System.out.println("Mobile Number  : " + mobileNumber);
        System.out.println("Email          : " + email);
        System.out.println("Initial Deposit: ₹" + initialDeposit);
        System.out.println("Account Type   : " + accountType);
        System.out.println("Current Balance: ₹" + balance);
    }
}
