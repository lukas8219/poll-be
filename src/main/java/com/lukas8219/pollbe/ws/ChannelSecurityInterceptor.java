package com.lukas8219.pollbe.ws;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.service.authentication.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChannelSecurityInterceptor implements ChannelInterceptor {

    private static final String USERNAME_HEADER = "login";
    private static final String PASSWORD_HEADER = "password";
    private final AuthenticationFacade authenticationFacade;

    @Override
    public Message<?> preSend(final Message<?> message, final MessageChannel channel) throws AuthenticationException {
        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT == accessor.getCommand()) {
            final String username = accessor.getFirstNativeHeader(USERNAME_HEADER);
            final String password = accessor.getFirstNativeHeader(PASSWORD_HEADER);
            final var user = authenticationFacade.authenticate(username, password);
            accessor.setUser(user);
        }

        if (StompCommand.SUBSCRIBE == accessor.getCommand()) {
            var user = (Authentication) accessor.getUser();
            var userDetails = (PollUserDetails) user.getPrincipal();
            if (!accessor.getDestination().contains(userDetails.getId().toString())) {
                throw new BadCredentialsException("Não é possível cadastrar nessa fila");
            }
        }
        return message;
    }
}
