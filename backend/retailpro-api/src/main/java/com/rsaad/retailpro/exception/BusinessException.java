package com.rsaad.retailpro.exception;

public class BusinessException extends RetailProException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}