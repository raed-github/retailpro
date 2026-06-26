package com.rsaad.retailpro.exception;

public class RetailProException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RetailProException(String message) {
        super(message);
    }

    public RetailProException(String message, Throwable cause) {
        super(message, cause);
    }
}