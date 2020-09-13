package com.kamiski.springbootjwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ExpiredTokenException extends RuntimeException{

    public ExpiredTokenException() {
        super("Token expired!");
    }

}
