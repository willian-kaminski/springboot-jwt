package com.kamiski.springbootjwt.controller.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthForm {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}
