package com.kamiski.springbootjwt.service;

import com.kamiski.springbootjwt.controller.form.UserForm;
import com.kamiski.springbootjwt.controller.form.UserFormUpdate;
import com.kamiski.springbootjwt.domain.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<Users> findAll();

    Optional<Users> findById(Long id);

    Optional<Users> findByEmail(String email);

    Users create(UserForm userForm);

    Users update(UserFormUpdate userFormUpdate);

    void deleteById(Long id);

}
