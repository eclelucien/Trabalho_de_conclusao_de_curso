package com.eclesiastelucien.com.lucienstore.commons.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthenticatedResourceException extends RuntimeException {

    public UnauthenticatedResourceException() {
        super("Failed to login, please retry");
    }

    public UnauthenticatedResourceException(String message) {
        super(message);
    }
}
