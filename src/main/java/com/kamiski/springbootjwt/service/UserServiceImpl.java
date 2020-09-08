package com.kamiski.springbootjwt.service;

import com.kamiski.springbootjwt.controller.form.UserForm;
import com.kamiski.springbootjwt.controller.form.UserFormUpdate;
import com.kamiski.springbootjwt.domain.User;
import com.kamiski.springbootjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User create(UserForm userForm) {

        User user = User.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .dateRegister(LocalDateTime.now())
                .isCredentialsNonExpired(true)
                .isNonExpired(true)
                .isNonLocked(true)
                .isEnabled(true)
                .build();

        return userRepository.save(user);
    }

    @Override
    public User update(UserFormUpdate userFormUpdate) {

        User user = User.builder()
                .name(userFormUpdate.getName())
                .email(userFormUpdate.getEmail())
                .password(userFormUpdate.getPassword())
                .profiles(userFormUpdate.getProfileList())
                .build();

        return userRepository.save(user);

    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
