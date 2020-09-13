package com.kamiski.springbootjwt.service.implementation;

import com.kamiski.springbootjwt.domain.Users;
import com.kamiski.springbootjwt.service.EmailService;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${api.email.email}")
    private String emailSender;

    @Value("${api.email.password}")
    private String password;

    @Value("${api.email.hostname}")
    private String hostname;

    @Value("${api.email.serverPort}")
    private String serverPort;

    @Value("${api.email.sslConnect}")
    private String sslConnect;

    @Override
    public void sendSimpleEmail(Users users) {

        try {

            Email email = new SimpleEmail();
            email.setHostName(this.hostname);
            email.setSmtpPort(Integer.parseInt(this.serverPort));
            email.setAuthenticator(new DefaultAuthenticator(this.emailSender, this.password));
            email.setSSLOnConnect(Boolean.parseBoolean(sslConnect));

            email.setFrom(this.emailSender);
            email.setSubject("SPRINGBOOT JWT");
            email.setMsg(String.format("Hello %s. This is you validation code %s", users.getName(), users.getValidationCode()));
            email.addTo(users.getEmail());
            email.send();

        }catch (Exception e){
            System.out.println("Error email >> " + e.getMessage());
        }

    }

}
