package com.kamiski.springbootjwt.configuration.secutiry;

import com.kamiski.springbootjwt.domain.Users;
import com.kamiski.springbootjwt.repository.UserRepository;
import com.kamiski.springbootjwt.service.implementation.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationByToken extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;

    public AuthenticationByToken(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recoverHeaderToken(httpServletRequest);

        if(tokenService.isTokenValid(token)){
            authClient(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    private void authClient(String token) {

        Long userId = tokenService.getUserId(token);
        Users users = userRepository.findById(userId).get();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(users, null, users.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    }

    private String recoverHeaderToken(HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }

        return token.substring(7);

    }

}
