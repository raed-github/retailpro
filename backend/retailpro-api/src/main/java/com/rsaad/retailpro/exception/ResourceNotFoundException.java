package com.rsaad.retailpro.exception;

public class ResourceNotFoundException extends RetailProException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}