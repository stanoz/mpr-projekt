package com.example.mpr_backend.exceptions;

public class InvalidPersonAgeException extends  RuntimeException{
    public InvalidPersonAgeException(String message) {
        super(message);
    }
}
