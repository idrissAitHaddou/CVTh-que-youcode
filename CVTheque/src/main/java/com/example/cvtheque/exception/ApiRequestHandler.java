package com.example.cvtheque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiRequestHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ApiException> handleApiRequestException(ApiException e){
        ApiException apiException = new ApiException(e.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiException,apiException.getHttpStatus());
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ApiException> handleApiRequestException(NotFoundException e){
        ApiException apiException = new ApiException(e.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiException,apiException.getHttpStatus());
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiException> handleBadRequestException(BadRequestException e){
        // Customise the exception body
        ApiException apiException = new ApiException(e.getMessage(),HttpStatus.NOT_FOUND);
        // Return the exception response
        return new ResponseEntity<>(apiException,apiException.getHttpStatus());
    }

}
