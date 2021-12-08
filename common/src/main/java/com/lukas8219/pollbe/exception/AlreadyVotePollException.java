package com.lukas8219.pollbe.exception;

public class AlreadyVotePollException extends UnprocessableEntityException {

    public AlreadyVotePollException() {
        super("Você já votou para esta assembleia");
    }
}
