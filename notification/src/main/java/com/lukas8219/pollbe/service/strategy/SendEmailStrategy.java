package com.lukas8219.pollbe.service.strategy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukas8219.pollbe.data.dto.EmailDTO;
import com.lukas8219.pollbe.data.dto.NotificationType;

public interface SendEmailStrategy<T> {

    NotificationType getName();

    ObjectMapper getObjectMapper();

    default T parse(String body) throws JsonProcessingException {
        var mapper = getObjectMapper();
        var type = new TypeReference<T>() {
        };
        return mapper.readValue(body, type);
    }

    EmailDTO convertMessage(String body) throws JsonProcessingException;

}
