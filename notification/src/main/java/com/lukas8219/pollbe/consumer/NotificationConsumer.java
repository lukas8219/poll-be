package com.lukas8219.pollbe.consumer;

public interface NotificationConsumer<T> {

    void onMessage(T message);

}
