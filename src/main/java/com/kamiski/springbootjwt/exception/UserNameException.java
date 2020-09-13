package com.kamiski.springbootjwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNameException extends RuntimeException{

    public UserNameException(String username) {
        super(String.format("Username '%s' already exists", username));
    }

}
