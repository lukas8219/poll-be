package com.lukas8219.pollbe.exception;

public class PollExpiredOrNotFoundException extends RuntimeException{

    public PollExpiredOrNotFoundException() {
        super("Votação não existe ou expirada");
    }

}
