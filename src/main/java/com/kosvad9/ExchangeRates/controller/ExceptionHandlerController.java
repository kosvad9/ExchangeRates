package com.kosvad9.ExchangeRates.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeException(RuntimeException exception){
        return new ResponseEntity<>("{\"message\": %s}".formatted(exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
