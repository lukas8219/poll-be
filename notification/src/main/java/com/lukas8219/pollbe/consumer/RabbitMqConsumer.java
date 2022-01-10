package com.lukas8219.pollbe.consumer;

import com.lukas8219.pollbe.data.dto.SendEmailDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMqConsumer implements NotificationConsumer<SendEmailDTO> {

    @Override
    @RabbitListener(queues = {"${notification.email}"})
    public void onMessage(@Payload SendEmailDTO message) {
        log.info("{}", message);
    }

}