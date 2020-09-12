package com.kamiski.springbootjwt.controller;

import com.kamiski.springbootjwt.controller.form.ProfileForm;
import com.kamiski.springbootjwt.domain.Profile;
import com.kamiski.springbootjwt.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<Profile> create(@RequestBody ProfileForm profileForm){
        return ResponseEntity.status(HttpStatus.CREATED).body(profileService.create(profileForm));
    }

    @GetMapping
    public ResponseEntity<List<Profile>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.findAll());
    }

}
