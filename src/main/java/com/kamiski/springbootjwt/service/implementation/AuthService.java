package com.kamiski.springbootjwt.service.implementation;

import com.kamiski.springbootjwt.domain.Users;
import com.kamiski.springbootjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByEmail(s);
        if(user.isPresent()){
            return user.get();
        }
        throw new UsernameNotFoundException("Dados inválidos!");
    }

}
