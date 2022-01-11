package com.lukas8219.pollbe.service.strategy;

import com.lukas8219.pollbe.data.dto.EmailDTO;
import com.lukas8219.pollbe.data.dto.NotificationType;
import org.springframework.stereotype.Component;

@Component
public class PollResultSendEmailStrategy implements SendEmailStrategy{

    @Override
    public NotificationType getName() {
        return NotificationType.EMAIL;
    }

    @Override
    public EmailDTO convertMessage(String body) {
        return new EmailDTO();
    }
}
