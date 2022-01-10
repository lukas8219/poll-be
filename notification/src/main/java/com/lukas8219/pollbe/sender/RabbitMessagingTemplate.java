package com.lukas8219.pollbe.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component("rabbitWrapper")
@RequiredArgsConstructor
@Slf4j
public class RabbitMessagingTemplate implements MessagingTemplate {

    private final RabbitTemplate template;

    @Override
    public void publish(String queueName, Object message) {
        template.convertAndSend(queueName, message);
    }

}
