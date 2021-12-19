package com.lukas8219.pollbe.ws;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${web-socket.endpoint}")
    private String WEB_SOCKET_ENDPOINT;

    @Value("${web-socket.cors}")
    private String ALLOWED_ORIGINS;

    private final ChannelSecurityInterceptor interceptor;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/user/");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user/");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(WEB_SOCKET_ENDPOINT).setAllowedOrigins(ALLOWED_ORIGINS);
        registry.addEndpoint(WEB_SOCKET_ENDPOINT).setAllowedOrigins(ALLOWED_ORIGINS).withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(interceptor);
    }
}
