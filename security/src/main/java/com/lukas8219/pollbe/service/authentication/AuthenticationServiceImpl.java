package com.lukas8219.pollbe.service.authentication;

import com.lukas8219.pollbe.service.PollUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager manager;
    private final PollUserDetailsService userDetailsService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userDetailsService.loadUserByUsername(username);
    }

    @Override
    public Authentication authenticate(String email, String password) {
        return manager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }
}
