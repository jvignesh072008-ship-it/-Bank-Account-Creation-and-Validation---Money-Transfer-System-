package com.bank.exception;

public class InvalidReceiverAccountException extends RuntimeException {

    public InvalidReceiverAccountException(String message) {
        super(message);
    }
}
