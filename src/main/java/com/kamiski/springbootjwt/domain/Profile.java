package com.kamiski.springbootjwt.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Profile implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    private String name;

    private LocalDateTime dateRegister = LocalDateTime.now();

    @Override
    public String getAuthority() {
        return this.name;
    }

}
