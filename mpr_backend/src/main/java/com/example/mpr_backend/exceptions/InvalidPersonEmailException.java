package com.example.mpr_backend.exceptions;

public class InvalidPersonEmailException extends RuntimeException{
    public InvalidPersonEmailException(String message) {
        super(message);
    }
}
