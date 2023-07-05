package com.eclesiastelucien.com.lucienstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenResourceException extends RuntimeException {

    public ForbiddenResourceException() {
        super("Access denied for this user");
    }

    public ForbiddenResourceException(String message) {
        super(message);
    }
}
