package com.kamiski.springbootjwt.service.implementation;

import com.kamiski.springbootjwt.domain.Users;
import com.kamiski.springbootjwt.exception.ExpiredTokenException;
import com.kamiski.springbootjwt.exception.SignatureTokenException;
import com.kamiski.springbootjwt.repository.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    @Value("${forum.jwt.secret}")
    private String secret;

    @Value("${forum.jwt.expiration}")
    private String expiration;

    public String generateToken(Authentication authentication){

        Users users = (Users) authentication.getPrincipal();
        Date today = new Date();

        return Jwts.builder()
                .setIssuer("SpringBoot JWT")
                .setSubject(users.getId().toString())
                .setIssuedAt(today)
                .setExpiration(new Date(today.getTime() + Long.parseLong(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    public boolean isTokenValid(String headerToken){

        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(headerToken);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public Long getUserId(String token){
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}
