package com.lukas8219.pollbe.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException{
    @Override
    public HttpStatus status() {
        return HttpStatus.NOT_FOUND;
    }

    public UserNotFoundException() {
        super("Usuário não encontrado");
    }
}
