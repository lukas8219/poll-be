package com.lukas8219.pollbe.service;

import com.lukas8219.pollbe.data.domain.AuthenticationProviderEnum;
import com.lukas8219.pollbe.data.domain.PollOAuth2User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PollOAuth2Service extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        var clientName = userRequest.getClientRegistration().getClientName();
        return new PollOAuth2User(super.loadUser(userRequest), AuthenticationProviderEnum.parse(clientName));
    }

}
