package com.example.mpr_frontend.exceptions;

public class PersonAlreadyExistException extends RuntimeException{
    public PersonAlreadyExistException(String message) {
        super(message);
    }
}
