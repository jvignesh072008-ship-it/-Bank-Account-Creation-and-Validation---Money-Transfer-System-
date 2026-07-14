package com.bank.exception;

public class InvalidCustomerNameException extends RuntimeException {

    public InvalidCustomerNameException(String message) {
        super(message);
    }
}
