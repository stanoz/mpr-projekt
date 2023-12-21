package com.example.mpr_backend.exceptions;

public class InvalidPersonLoginException extends RuntimeException{
    public InvalidPersonLoginException(String message) {
        super(message);
    }
}
