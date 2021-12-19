package com.lukas8219.pollbe.ws;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.exception.ForbiddenException;
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

    private final String PASSWORD_HEADER = "password";
    private final AuthenticationFacade authenticationFacade;

    @Override
    public Message<?> preSend(final Message<?> message, final MessageChannel channel) throws AuthenticationException {
        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT == accessor.getCommand()) {
            final String jwt = accessor.getFirstNativeHeader(PASSWORD_HEADER);
            final var user = authenticationFacade.authenticateByWebSocket(jwt);
            accessor.setUser(user);
        }

        if (StompCommand.SUBSCRIBE == accessor.getCommand()) {
            final var user = (Authentication) accessor.getUser();
            final var userDetails = (PollUserDetails) user.getPrincipal();
            if (!accessor.getDestination().contains(userDetails.getId().toString())) {
                throw new ForbiddenException();
            }
        }
        return message;
    }
}
