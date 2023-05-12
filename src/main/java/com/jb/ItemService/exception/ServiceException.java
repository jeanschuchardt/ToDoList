package com.jb.ItemService.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ServiceException extends RuntimeException {
    private String message;
    private HttpStatus status;
    
    public ServiceException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
