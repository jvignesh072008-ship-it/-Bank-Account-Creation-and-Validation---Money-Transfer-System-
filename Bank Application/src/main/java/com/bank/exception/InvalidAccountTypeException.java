package com.bank.exception;

public class InvalidAccountTypeException extends RuntimeException {

    public InvalidAccountTypeException(String message) {
        super(message);
    }
}
