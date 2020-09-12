package com.kamiski.springbootjwt.controller.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class ProfileForm {

    @NotEmpty
    @Length(min = 5)
    private String name;

}
