package com.kamiski.springbootjwt.controller.form;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserAuthForm {

    @NotNull
    @NotEmpty
    private String email;

    private Integer code;

}
