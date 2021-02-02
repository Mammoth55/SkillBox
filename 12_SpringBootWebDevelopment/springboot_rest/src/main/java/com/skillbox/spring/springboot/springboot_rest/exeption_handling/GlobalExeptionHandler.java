package com.skillbox.spring.springboot.springboot_rest.exeption_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler
    public final ResponseEntity<ApiError> handleException(EntityNotFoundException ex) {
        ApiError error = new ApiError();
        error.setInfo(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<ApiError> handleException(Exception ex) {
        ApiError error = new ApiError();
        error.setInfo(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}