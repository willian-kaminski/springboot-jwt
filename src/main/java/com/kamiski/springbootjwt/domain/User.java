package com.kamiski.springbootjwt.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class User {

    @Id
    private Long id;

    private String name;

    private String email;

    private String password;

    private LocalDateTime dateRegister = LocalDateTime.now();

}
