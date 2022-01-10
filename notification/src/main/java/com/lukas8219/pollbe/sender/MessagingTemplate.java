package com.lukas8219.pollbe.sender;

public interface MessagingTemplate {

    void publish(String queueName, Object message);

}
