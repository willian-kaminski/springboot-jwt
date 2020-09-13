package com.kamiski.springbootjwt.validation;

import com.kamiski.springbootjwt.exception.ResourceNotFoundException;
import com.kamiski.springbootjwt.exception.UserNameException;
import com.kamiski.springbootjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecordValidation {

    @Autowired
    private UserRepository userRepository;

    public void verifyIfUserExistsById(Long id){
        if(!userRepository.findById(id).isPresent()){
            throw new ResourceNotFoundException(id);
        }
    }

    public void verifyIfUsernameAlreadyExists(String username){
        if(userRepository.existsUserByEmail(username)){
            throw new UserNameException(username);
        }
    }

}
