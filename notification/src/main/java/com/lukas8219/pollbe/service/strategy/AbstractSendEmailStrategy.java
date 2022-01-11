package com.lukas8219.pollbe.service.strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractSendEmailStrategy<T> implements SendEmailStrategy<T> {

    private final ObjectMapper mapper;

    @Override
    public ObjectMapper getObjectMapper() {
        return mapper;
    }
}
