package com.kamiski.springbootjwt.repository;

import com.kamiski.springbootjwt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsUserByEmail(String email);

}
