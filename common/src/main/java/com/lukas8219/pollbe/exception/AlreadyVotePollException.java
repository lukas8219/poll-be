package com.lukas8219.pollbe.exception;

public class AlreadyVotePollException extends UnprocessableEntityException {

    public AlreadyVotePollException() {
        super("poll.already.voted");
    }
}
