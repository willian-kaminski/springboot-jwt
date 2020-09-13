package com.kamiski.springbootjwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserLocked extends RuntimeException{

    public UserLocked() {
        super("Accound Locked, pleace validate your account!");
    }

}
