package com.kamiski.springbootjwt.service;

import com.kamiski.springbootjwt.controller.dto.TokenDto;
import com.kamiski.springbootjwt.controller.form.AuthForm;
import com.kamiski.springbootjwt.service.implementation.TokenService;
import org.springframework.security.core.Authentication;

public interface AuthService {

    TokenDto authenticateClient(AuthForm authForm, TokenService tokenService);

}
