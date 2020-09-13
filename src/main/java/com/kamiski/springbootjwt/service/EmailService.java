package com.kamiski.springbootjwt.service;

import com.kamiski.springbootjwt.domain.Users;

public interface EmailService {
    
    void sendSimpleEmail(Users user);
    
}
