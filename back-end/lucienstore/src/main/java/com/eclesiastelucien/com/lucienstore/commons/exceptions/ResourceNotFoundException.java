package com.eclesiastelucien.com.lucienstore.commons.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("The specified resource was not found.");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
