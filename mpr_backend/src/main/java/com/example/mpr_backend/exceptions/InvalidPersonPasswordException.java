package com.example.mpr_backend.exceptions;

public class InvalidPersonPasswordException extends RuntimeException{
    public InvalidPersonPasswordException(String message) {
        super(message);
    }
}
