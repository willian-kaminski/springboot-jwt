package com.kamiski.springbootjwt.controller;

import com.kamiski.springbootjwt.controller.dto.TokenDto;
import com.kamiski.springbootjwt.controller.form.AuthForm;
import com.kamiski.springbootjwt.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid AuthForm authForm){

        UsernamePasswordAuthenticationToken dataAuth = new UsernamePasswordAuthenticationToken(authForm.getEmail(), authForm.getPassword());

        try {

            Authentication authentication = authenticationManager.authenticate(dataAuth);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.status(HttpStatus.OK).body(new TokenDto(token, "Bearer"));

        }catch (AuthenticationException authenticationException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

}
