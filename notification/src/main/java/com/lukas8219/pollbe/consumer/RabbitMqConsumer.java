package com.lukas8219.pollbe.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumer implements NotificationConsumer {

    @RabbitListener(queues = {"${notification.email}"})
    public void receive(@Payload String message) {
        System.out.println(message);
    }


}