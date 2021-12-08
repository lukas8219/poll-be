package com.lukas8219.pollbe.exception;

import org.springframework.http.HttpStatus;

public class PollExpiredOrNotFoundException extends CustomException {

    @Override
    public HttpStatus status() {
        return HttpStatus.NOT_FOUND;
    }

    public PollExpiredOrNotFoundException() {
        super("Votação não existe ou expirada");
    }

}
