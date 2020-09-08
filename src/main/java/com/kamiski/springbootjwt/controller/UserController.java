package com.kamiski.springbootjwt.controller;

import com.kamiski.springbootjwt.controller.form.UserForm;
import com.kamiski.springbootjwt.controller.form.UserFormUpdate;
import com.kamiski.springbootjwt.domain.Users;
import com.kamiski.springbootjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<Users> create(@RequestBody @Valid UserForm userForm){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userForm));
    }

    @PutMapping
    public ResponseEntity<Users> update(@RequestBody @Valid UserFormUpdate userFormUpdate){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.update(userFormUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
