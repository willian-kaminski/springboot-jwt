package com.kamiski.springbootjwt.controller.form;

import com.kamiski.springbootjwt.domain.Profile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserFormUpdate {

    @NotNull
    @NotEmpty
    private Long id;

    @NotNull
    @NotEmpty
    @Length(min = 5, max = 150)
    private String name;

    @NotNull
    @NotEmpty
    @Email
    @Length(min = 5, max = 150)
    private String email;

    @NotNull
    @NotEmpty
    @Length(min = 7, max = 150)
    private String password;

    private List<Profile> profileList;

}
