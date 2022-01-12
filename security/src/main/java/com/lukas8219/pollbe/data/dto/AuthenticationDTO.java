package com.lukas8219.pollbe.data.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthenticationDTO {

    @NotNull(message = "{authentication.email.notEmpty}")
    private String email;
    @NotNull(message = "{authentication.password.notEmpty}")
    private String password;

}
