package com.example.mpr_frontend.exception_handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class PersonExceptionHandler extends ResponseEntityExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(PersonExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    protected String handleExceptionFromBackend(HttpClientErrorException ex, RedirectAttributes redirectAttributes) {
        logger.info(ex.getMessage());
        String errorMessage = ex.getMessage()
                .replaceAll("^\\s*\\d{3}\\s*\\w+\\s*\\w+:\\s*", "");
        redirectAttributes.addAttribute("Error",
                "You have provided not permitted action: \n"
                + errorMessage);
        return ("redirect:/showAll?error=true");
    }
}
