package com.example.mpr_frontend.exceptions;

public class InvalidPersonLoginException extends RuntimeException{
    public InvalidPersonLoginException(String message) {
        super(message);
    }
}
