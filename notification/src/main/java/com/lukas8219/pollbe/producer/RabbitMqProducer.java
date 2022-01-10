package com.lukas8219.pollbe.producer;

import com.lukas8219.pollbe.config.QueueDestinations;
import com.lukas8219.pollbe.data.dto.SendEmailDTO;
import com.lukas8219.pollbe.sender.MessagingTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMqProducer implements NotificationProducer<SendEmailDTO> {

    private final MessagingTemplate messagingTemplate;
    private final QueueDestinations config;

    @Override
    public void publish(SendEmailDTO message) {
        messagingTemplate.publish(config.getEmail(), message);
    }

}
