package com.kamiski.springbootjwt.service;

import com.kamiski.springbootjwt.domain.User;
import com.kamiski.springbootjwt.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

        User user = (User) authentication.getPrincipal();
        Date today = new Date();

        return Jwts.builder()
                .setIssuer("")
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(today.getTime() + Long.parseLong(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    public boolean isTokenValid(String headerToken){
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(headerToken);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Long getUserId(String token){
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}