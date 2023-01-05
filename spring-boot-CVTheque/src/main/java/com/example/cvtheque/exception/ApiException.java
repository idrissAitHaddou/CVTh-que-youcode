package com.example.cvtheque.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

    public ApiException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
