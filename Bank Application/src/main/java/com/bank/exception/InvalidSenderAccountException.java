package com.bank.exception;

public class InvalidSenderAccountException extends RuntimeException {

    public InvalidSenderAccountException(String message) {
        super(message);
    }
}
