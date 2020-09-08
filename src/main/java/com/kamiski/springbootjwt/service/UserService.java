package com.kamiski.springbootjwt.service;

import com.kamiski.springbootjwt.controller.form.UserForm;
import com.kamiski.springbootjwt.controller.form.UserFormUpdate;
import com.kamiski.springbootjwt.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User create(UserForm userForm);

    User update(UserFormUpdate userFormUpdate);

    void deleteById(Long id);

}
