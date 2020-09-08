package com.kamiski.springbootjwt.service;

import com.kamiski.springbootjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    private String secret;

    private String expiration;

    public String generateToken(Authentication authentication){
        return null;
    }

    public boolean isTokenValid(String headerToken){
        return false;
    }

    public Long getUserId(String token){
        return null;
    }

}
