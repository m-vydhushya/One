package com.spring.one.GlobalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)  // Handles validation errors
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>(); // Store errors

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors(); // Get field errors

        for (FieldError error : fieldErrors) {
            errors.put(error.getField(), error.getDefaultMessage()); // Field name : Error message
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST); // 400 status code
    }


}
