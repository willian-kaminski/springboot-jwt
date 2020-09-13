package com.kamiski.springbootjwt.controller;

import com.kamiski.springbootjwt.controller.form.UserAuthForm;
import com.kamiski.springbootjwt.controller.form.UserForm;
import com.kamiski.springbootjwt.controller.form.UserFormUpdate;
import com.kamiski.springbootjwt.domain.Users;
import com.kamiski.springbootjwt.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<List<Users>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(usersService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usersService.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<Users> create(@RequestBody @Valid UserForm userForm){
        Users users = usersService.create(userForm);
        usersService.sendValidationCode(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }

    @PutMapping
    public ResponseEntity<Users> update(@RequestBody @Valid UserFormUpdate userFormUpdate){
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.update(userFormUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        usersService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/auth")
    public ResponseEntity authenticateUserByCode(@RequestBody @Valid UserAuthForm authForm){
        return usersService.setUserIsValid(authForm);
    }

}
