package com.lukas8219.pollbe.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukas8219.pollbe.data.domain.AuthenticationProviderEnum;
import com.lukas8219.pollbe.data.domain.PollOAuth2User;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.service.UserCreateServiceImpl;
import com.lukas8219.pollbe.service.authentication.AuthenticationFacade;
import com.lukas8219.pollbe.service.authentication.token.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuthAuthenticationSucessHandler implements AuthenticationSuccessHandler {

    private final UserCreateServiceImpl userCreateService;
    private final TokenService tokenService;
    @Value("${frontend-url}")
    private String FRONTEND_URL;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        var user = (PollOAuth2User) authentication.getPrincipal();

        var createdUser = userCreateService.createOrFindByGoogle(user);
        var details = new PollUserDetails(createdUser);
        var token = tokenService.generateToken(details);

        response.sendRedirect(String.format("%s/?token=%s", FRONTEND_URL, token.getToken()));

    }
}
