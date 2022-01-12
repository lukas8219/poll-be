package com.lukas8219.pollbe.data.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class PollOAuth2User implements OAuth2User {

    private final OAuth2User user;
    private final AuthenticationProviderEnum provider;

    @Override
    public Map<String, Object> getAttributes() {
        return user.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getName() {
        return user.getAttribute("name");
    }

    public String getEmail() {
        return user.getAttribute("email");
    }

    public AuthenticationProviderEnum getProvider() {
        return provider;
    }
}
