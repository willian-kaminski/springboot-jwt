package com.kamiski.springbootjwt.service;

import com.kamiski.springbootjwt.controller.form.ProfileForm;
import com.kamiski.springbootjwt.domain.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileService {

    Profile create(ProfileForm profileForm);

    List<Profile> findAll();

    Optional<Profile> findById(Long id);

}
