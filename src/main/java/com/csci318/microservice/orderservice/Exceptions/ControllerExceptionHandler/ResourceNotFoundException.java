package com.csci318.microservice.orderservice.Exceptions.ControllerExceptionHandler;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseControllerException {
    public ResourceNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
