package com.lukas8219.pollbe.producer;

public interface NotificationProducer<T> {

    void publish(T message);

}
