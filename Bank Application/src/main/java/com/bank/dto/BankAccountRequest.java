package com.bank.dto;

public class BankAccountRequest {

    private String accountNumber;
    private String customerName;
    private String mobileNumber;
    private String email;
    private double initialDeposit;
    private String accountType;

    public BankAccountRequest(String accountNumber, String customerName, String mobileNumber, String email, double initialDeposit, String accountType) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.initialDeposit = initialDeposit;
        this.accountType = accountType;
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
}
