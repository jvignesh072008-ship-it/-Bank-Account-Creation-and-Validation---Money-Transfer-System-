package com.bank.service;

import com.bank.dto.TransactionRequest;
import com.bank.model.BankAccount;
import com.bank.model.Transaction;
import com.bank.repository.BankAccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.exception.InvalidSenderAccountException;
import com.bank.exception.InvalidReceiverAccountException;
import com.bank.exception.SameAccountTransferException;
import com.bank.exception.InvalidTransferAmountException;
import com.bank.exception.InsufficientBalanceException;
import com.bank.exception.InvalidPinException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction transfer(TransactionRequest request) {
        BankAccount sender = validateSenderAccount(request.getSenderAccountNumber());
        BankAccount receiver = validateReceiverAccount(request.getReceiverAccountNumber());
        validateSameAccount(request.getSenderAccountNumber(), request.getReceiverAccountNumber());
        validateTransferAmount(request.getTransferAmount());
        validatePin(request.getTransactionPin());
        validateBalance(sender.getBalance(), request.getTransferAmount());

        sender.debit(request.getTransferAmount());
        receiver.credit(request.getTransferAmount());
        bankAccountRepository.save(sender);
        bankAccountRepository.save(receiver);

        Transaction transaction = new Transaction(sender.getAccountNumber(), receiver.getAccountNumber(), request.getTransferAmount(), sender.getBalance());
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    private BankAccount validateSenderAccount(String senderAccountNumber) {
        if (senderAccountNumber == null || !senderAccountNumber.matches("\\d{10}")) {
            throw new InvalidSenderAccountException("Sender account number must contain exactly 10 digits");
        }
        BankAccount sender = bankAccountRepository.findByAccountNumber(senderAccountNumber);
        if (sender == null) {
            throw new InvalidSenderAccountException("Sender account " + senderAccountNumber + " does not exist");
        }
        return sender;
    }

    private BankAccount validateReceiverAccount(String receiverAccountNumber) {
        if (receiverAccountNumber == null || !receiverAccountNumber.matches("\\d{10}")) {
            throw new InvalidReceiverAccountException("Receiver account number must contain exactly 10 digits");
        }
        BankAccount receiver = bankAccountRepository.findByAccountNumber(receiverAccountNumber);
        if (receiver == null) {
            throw new InvalidReceiverAccountException("Receiver account " + receiverAccountNumber + " does not exist");
        }
        return receiver;
    }

    private void validateSameAccount(String senderAccountNumber, String receiverAccountNumber) {
        if (senderAccountNumber.equals(receiverAccountNumber)) {
            throw new SameAccountTransferException("Sender and receiver accounts cannot be the same");
        }
    }

    private void validateTransferAmount(double transferAmount) {
        if (transferAmount <= 0) {
            throw new InvalidTransferAmountException("Transfer amount must be greater than 0");
        }
        if (transferAmount > 50000) {
            throw new InvalidTransferAmountException("Maximum transfer amount is ₹50,000");
        }
    }

    private void validatePin(String transactionPin) {
        if (transactionPin == null || !transactionPin.matches("\\d{4}")) {
            throw new InvalidPinException("PIN must contain exactly 4 digits");
        }
    }

    private void validateBalance(double accountBalance, double transferAmount) {
        if (accountBalance - transferAmount < 1000) {
            throw new InsufficientBalanceException("Sender must maintain a minimum balance of ₹1000 after transfer");
        }
    }
}
