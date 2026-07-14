package com.bank.service;

import com.bank.dto.BankAccountRequest;
import com.bank.model.BankAccount;
import com.bank.repository.BankAccountRepository;
import com.bank.exception.InvalidAccountNumberException;
import com.bank.exception.InvalidCustomerNameException;
import com.bank.exception.InvalidMobileNumberException;
import com.bank.exception.InvalidEmailException;
import com.bank.exception.InvalidDepositException;
import com.bank.exception.InvalidAccountTypeException;
import com.bank.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount createAccount(BankAccountRequest request) {
        validateAccountNumber(request.getAccountNumber());
        validateCustomerName(request.getCustomerName());
        validateMobileNumber(request.getMobileNumber());
        validateEmail(request.getEmail());
        validateAccountType(request.getAccountType());
        validateInitialDeposit(request.getInitialDeposit(), request.getAccountType());
        BankAccount account = new BankAccount(request.getAccountNumber(), request.getCustomerName(), request.getMobileNumber(), request.getEmail(), request.getInitialDeposit(), request.getAccountType());
        return bankAccountRepository.save(account);
    }

    public BankAccount getAccountByAccountNumber(String accountNumber) {
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new ResourceNotFoundException("Account not found with account number: " + accountNumber);
        }
        return account;
    }

    public List<BankAccount> getAllAccounts() {
        return List.copyOf(bankAccountRepository.findAll());
    }

    private void validateAccountNumber(String accountNumber) {
        if (accountNumber == null || !accountNumber.matches("\\d{10}")) {
            throw new InvalidAccountNumberException("Account number must contain exactly 10 digits");
        }
        if (bankAccountRepository.existsByAccountNumber(accountNumber)) {
            throw new InvalidAccountNumberException("Account number " + accountNumber + " already exists");
        }
    }

    private void validateCustomerName(String customerName) {
        if (customerName == null || !customerName.matches("[a-zA-Z ]+")) {
            throw new InvalidCustomerNameException("Customer name should contain only alphabets and spaces");
        }
    }

    private void validateMobileNumber(String mobileNumber) {
        if (mobileNumber == null || !mobileNumber.matches("[6789]\\d{9}")) {
            throw new InvalidMobileNumberException("Mobile number must contain exactly 10 digits and start with 6, 7, 8, or 9");
        }
    }

    private void validateEmail(String email) {
        if (email == null || !email.contains("@") || !email.endsWith(".com")) {
            throw new InvalidEmailException("Email should contain '@' and end with '.com'");
        }
    }

    private void validateAccountType(String accountType) {
        if (accountType == null || !(accountType.equalsIgnoreCase("Savings") || accountType.equalsIgnoreCase("Current"))) {
            throw new InvalidAccountTypeException("Account type must be Savings or Current");
        }
    }

    private void validateInitialDeposit(double initialDeposit, String accountType) {
        if (accountType.equalsIgnoreCase("Savings") && initialDeposit < 1000) {
            throw new InvalidDepositException("Initial deposit for Savings account must be at least ₹1000");
        }
        if (accountType.equalsIgnoreCase("Current") && initialDeposit < 5000) {
            throw new InvalidDepositException("Initial deposit for Current account must be at least ₹5000");
        }
    }
}
