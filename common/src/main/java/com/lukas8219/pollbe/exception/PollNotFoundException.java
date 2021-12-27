package com.lukas8219.pollbe.exception;

import org.springframework.http.HttpStatus;

public class PollNotFoundException extends CustomException{
    @Override
    public HttpStatus status() {
        return HttpStatus.NOT_FOUND;
    }

    public PollNotFoundException() {
        super("poll.not.found");
    }
}
