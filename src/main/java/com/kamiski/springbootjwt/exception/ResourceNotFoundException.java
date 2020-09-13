package com.kamiski.springbootjwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Long id) {
        super(String.format("Resource not found for id '%s' ", id));
    }

}
