package com.kamiski.springbootjwt.service.implementation;

import com.kamiski.springbootjwt.controller.form.ProfileForm;
import com.kamiski.springbootjwt.domain.Profile;
import com.kamiski.springbootjwt.repository.ProfileRepository;
import com.kamiski.springbootjwt.service.ProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    @Override
    public Profile create(ProfileForm profileForm) {

        Profile profile = Profile.builder()
                .name(profileForm.getName())
                .dateRegister(LocalDateTime.now())
                .build();

        return profileRepository.save(profile);
    }

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Optional<Profile> findById(Long id) {
        return profileRepository.findById(id);
    }

}
