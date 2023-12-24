package com.example.mpr_frontend.exception_handlers;

import com.example.mpr_frontend.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class PersonExceptionHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(PersonNotFoundException.class)
//    protected ResponseEntity<String> handleNotFound(RuntimeException ex, WebRequest request){
//        return ResponseEntity.notFound().build();
//    }
//    @ExceptionHandler(PersonAlreadyExistException.class)
//    protected ResponseEntity<String> handleAlreadyExist(RuntimeException ex, WebRequest request){
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }
//    @ExceptionHandler({InvalidPersonAgeException.class, InvalidPersonEmailException.class,
//            InvalidPersonLoginException.class, InvalidPersonNameException.class, InvalidPersonPasswordException.class})
//    protected ResponseEntity<String> handleInvalidArgument(RuntimeException ex, WebRequest request){
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }
    @ExceptionHandler(Exception.class)
    protected String handleExceptionFromBackend(HttpClientErrorException ex, RedirectAttributes redirectAttributes) {
        String errorMessage = ex.getMessage()
                .replaceAll("^\\s*\\d{3}\\s*\\w+\\s*\\w+:\\s*", "");
        redirectAttributes.addAttribute("Error",
                "You have provided not permitted action: \n"
                + errorMessage);
        return ("redirect:/showAll?error=true");
    }
}
