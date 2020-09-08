package com.kamiski.springbootjwt.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    private String name;

    private LocalDateTime dateRegister = LocalDateTime.now();

}
