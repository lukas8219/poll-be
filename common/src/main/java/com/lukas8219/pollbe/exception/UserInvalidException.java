package com.lukas8219.pollbe.exception;

import org.springframework.http.HttpStatus;

public class UserInvalidException extends CustomException{
    @Override
    public HttpStatus status() {
        return HttpStatus.UNAUTHORIZED;
    }

    public UserInvalidException() {
        super("Credenciais inválidas");
    }
}
