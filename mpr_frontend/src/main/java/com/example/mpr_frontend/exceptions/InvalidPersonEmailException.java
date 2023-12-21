package com.example.mpr_frontend.exceptions;

public class InvalidPersonEmailException extends RuntimeException{
    public InvalidPersonEmailException(String message) {
        super(message);
    }
}
