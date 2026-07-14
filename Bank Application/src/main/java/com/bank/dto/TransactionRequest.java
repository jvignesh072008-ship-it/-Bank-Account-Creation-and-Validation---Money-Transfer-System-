package com.bank.dto;

public class TransactionRequest {

    private String senderAccountNumber;
    private String receiverAccountNumber;
    private double transferAmount;
    private String transactionPin;

    public TransactionRequest(String senderAccountNumber, String receiverAccountNumber, double transferAmount, String transactionPin) {
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.transferAmount = transferAmount;
        this.transactionPin = transactionPin;
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

    public String getTransactionPin() {
        return transactionPin;
    }
}
