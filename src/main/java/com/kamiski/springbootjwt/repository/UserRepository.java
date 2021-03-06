package com.kamiski.springbootjwt.repository;

import com.kamiski.springbootjwt.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Boolean existsUserByEmail(String email);

    Boolean existsUserByEmailAndValidationCode(String email, Integer code);

    Optional<Users> findByEmail(String email);

}
