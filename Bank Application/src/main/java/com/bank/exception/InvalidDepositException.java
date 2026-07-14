package com.bank.exception;

public class InvalidDepositException extends RuntimeException {

    public InvalidDepositException(String message) {
        super(message);
    }
}
