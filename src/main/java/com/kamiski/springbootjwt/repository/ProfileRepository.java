package com.kamiski.springbootjwt.repository;

import com.kamiski.springbootjwt.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
