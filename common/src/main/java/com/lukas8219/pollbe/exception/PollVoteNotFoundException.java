package com.lukas8219.pollbe.exception;

import org.springframework.http.HttpStatus;

public class PollVoteNotFoundException extends CustomException {

    public PollVoteNotFoundException() {
        super("poll.vote.not.found");
    }

    @Override
    public HttpStatus status() {
        return HttpStatus.NOT_FOUND;
    }
}
