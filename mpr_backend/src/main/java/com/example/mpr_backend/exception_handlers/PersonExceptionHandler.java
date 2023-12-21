package com.example.mpr_backend.exception_handlers;

import com.example.mpr_backend.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PersonExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PersonNotFoundException.class)
    protected ResponseEntity<String> handleNotFound(RuntimeException ex, WebRequest request){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(PersonAlreadyExistException.class)
    protected ResponseEntity<String> handleAlreadyExist(RuntimeException ex, WebRequest request){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler({InvalidPersonAgeException.class, InvalidPersonEmailException.class,
            InvalidPersonLoginException.class, InvalidPersonNameException.class, InvalidPersonPasswordException.class})
    protected ResponseEntity<String> handleInvalidArgument(RuntimeException ex, WebRequest request){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
