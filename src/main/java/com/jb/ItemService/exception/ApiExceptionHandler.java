package com.jb.ItemService.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException se) {
        ApiException apiException = new ApiException(
                se.getMessage(),
                se.getStatus()
        );
        return new ResponseEntity<>(apiException, se.getStatus());
    }
    
}

