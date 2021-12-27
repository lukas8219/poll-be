package com.lukas8219.pollbe.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends CustomException{
    @Override
    public HttpStatus status() {
        return HttpStatus.FORBIDDEN;
    }

    public ForbiddenException() {
        super("forbidden.exception");
    }
}
