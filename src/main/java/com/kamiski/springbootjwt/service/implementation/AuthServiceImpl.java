package com.kamiski.springbootjwt.service.implementation;

import com.kamiski.springbootjwt.controller.dto.TokenDto;
import com.kamiski.springbootjwt.controller.form.AuthForm;
import com.kamiski.springbootjwt.exception.AuthException;
import com.kamiski.springbootjwt.exception.UserLocked;
import com.kamiski.springbootjwt.repository.UserRepository;
import com.kamiski.springbootjwt.service.AuthService;
import com.kamiski.springbootjwt.validation.RecordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public TokenDto authenticateClient(AuthForm authForm, TokenService tokenService) {

        try {

            UsernamePasswordAuthenticationToken dataAuth = new UsernamePasswordAuthenticationToken(authForm.getEmail(), authForm.getPassword());
            Authentication authentication = authenticationManager.authenticate(dataAuth);
            String token = tokenService.generateToken(authentication);
            return new TokenDto(token, "Bearer");

        }catch (LockedException e){
            throw new UserLocked();
        }catch (AuthenticationException authenticationException){
            throw new AuthException();
        }

    }

}
