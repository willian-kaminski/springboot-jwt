package com.kamiski.springbootjwt.validation;

import com.kamiski.springbootjwt.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class RecordValidation {

    public void verifyIfEmailExists(String email, UserRepository repository){
    }

}
