package com.lukas8219.pollbe.exception;

import org.springframework.http.HttpStatus;

public class MissingParametersException extends CustomException {
    public MissingParametersException(String parameter) {
        super(String.format("Por favor informe o %s", parameter));
    }

    @Override
    public HttpStatus status() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
