package com.lukas8219.pollbe.exception;

import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends CustomException{

    @Override
    public HttpStatus status() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }

    public UnprocessableEntityException(String message) {
        super(message);
    }
}
