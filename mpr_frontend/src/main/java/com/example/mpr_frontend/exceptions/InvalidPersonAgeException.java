package com.example.mpr_frontend.exceptions;

public class InvalidPersonAgeException extends  RuntimeException{
    public InvalidPersonAgeException(String message) {
        super(message);
    }
}
