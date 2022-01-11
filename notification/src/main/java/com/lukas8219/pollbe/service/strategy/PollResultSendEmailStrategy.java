package com.lukas8219.pollbe.service.strategy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukas8219.pollbe.data.dto.EmailDTO;
import com.lukas8219.pollbe.data.dto.NotificationType;
import com.lukas8219.pollbe.data.dto.PollResultSendEmailDTO;
import org.springframework.stereotype.Component;

@Component
public class PollResultSendEmailStrategy extends AbstractSendEmailStrategy<PollResultSendEmailDTO> {

    public PollResultSendEmailStrategy(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public NotificationType getName() {
        return NotificationType.EMAIL;
    }

    @Override
    public EmailDTO convertMessage(String body) throws JsonProcessingException {
        var dto = parse(body);
        return new EmailDTO();
    }
}
