package com.red.pharma.exception;

public class RequestValidationException extends RuntimeException{
    public RequestValidationException(String errorMessage) {
        super(errorMessage);
    }

}
