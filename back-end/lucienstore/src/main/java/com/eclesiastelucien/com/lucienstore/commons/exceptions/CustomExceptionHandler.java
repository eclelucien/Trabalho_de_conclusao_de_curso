package com.eclesiastelucien.com.lucienstore.commons.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<String> handleInvalidException(InvalidFieldException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}