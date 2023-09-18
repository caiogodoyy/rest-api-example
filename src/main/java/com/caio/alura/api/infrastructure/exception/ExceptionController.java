package com.caio.alura.api.infrastructure.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ValidationException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ValidationException.class) 
    public ResponseEntity<?> handleValidationException(ValidationException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    
}
