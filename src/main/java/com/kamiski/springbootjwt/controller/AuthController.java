package com.kamiski.springbootjwt.controller;

import com.kamiski.springbootjwt.controller.dto.TokenDto;
import com.kamiski.springbootjwt.controller.form.AuthForm;
import com.kamiski.springbootjwt.service.AuthService;
import com.kamiski.springbootjwt.service.implementation.TokenService;
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
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid AuthForm authForm){
        return ResponseEntity.status(HttpStatus.OK).body(authService.authenticateClient(authForm, tokenService));
    }

}
