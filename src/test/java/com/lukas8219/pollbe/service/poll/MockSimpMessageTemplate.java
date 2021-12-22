package com.lukas8219.pollbe.service.poll;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;

public class MockSimpMessageTemplate<T> implements SimpMessageSendingOperations {

    private final List<Long> usersSent = new ArrayList<>();
    private final Class<T> payloadClass;
    private final String acceptedDestination;

    public MockSimpMessageTemplate(Class<T> payloadClass, String acceptedDestination) {
        this.payloadClass = payloadClass;
        this.acceptedDestination = acceptedDestination;
    }

    public List<Long> getUsersSent() {
        return usersSent;
    }

    public void clear() {
        usersSent.clear();
    }

    @Override
    public void convertAndSendToUser(String user, String destination, Object payload) throws MessagingException {
        usersSent.add(Long.valueOf(user));

        if (payload == null) {
            fail("Payload cannot be Null");
        }

        if (!payload.getClass().equals(payloadClass)) {
            fail("Payload should be an Instance of PollResultDTO");
        }

        if (!acceptedDestination.equals(destination)) {
            fail(String.format("Destination exp be %s", acceptedDestination));
        }

    }

    @Override
    public void convertAndSendToUser(String user, String destination, Object payload, Map<String, Object> headers) throws MessagingException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void convertAndSendToUser(String user, String destination, Object payload, MessagePostProcessor postProcessor) throws MessagingException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void convertAndSendToUser(String user, String destination, Object payload, Map<String, Object> headers, MessagePostProcessor postProcessor) throws MessagingException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void send(Message<?> message) throws MessagingException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void send(String destination, Message<?> message) throws MessagingException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void convertAndSend(Object payload) throws MessagingException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void convertAndSend(String destination, Object payload) throws MessagingException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void convertAndSend(String destination, Object payload, Map<String, Object> headers) throws MessagingException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void convertAndSend(Object payload, MessagePostProcessor postProcessor) throws MessagingException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void convertAndSend(String destination, Object payload, MessagePostProcessor postProcessor) throws MessagingException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void convertAndSend(String destination, Object payload, Map<String, Object> headers, MessagePostProcessor postProcessor) throws MessagingException {
        throw new RuntimeException("Not implemented");
    }
}
