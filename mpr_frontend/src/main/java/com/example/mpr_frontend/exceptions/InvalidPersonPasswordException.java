package com.example.mpr_frontend.exceptions;

public class InvalidPersonPasswordException extends RuntimeException{
    public InvalidPersonPasswordException(String message) {
        super(message);
    }
}
