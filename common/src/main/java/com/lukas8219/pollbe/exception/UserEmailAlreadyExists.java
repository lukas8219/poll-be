package com.lukas8219.pollbe.exception;

import org.springframework.http.HttpStatus;

public class UserEmailAlreadyExists extends CustomException {
    public UserEmailAlreadyExists() {
        super("Este email já existe!");
    }

    @Override
    public HttpStatus status() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
