package com.lukas8219.pollbe.api;

import com.lukas8219.pollbe.data.dto.AuthenticationDTO;
import com.lukas8219.pollbe.data.dto.TokenDTO;
import com.lukas8219.pollbe.service.authentication.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/authenticate")
public class AuthenticationApi {

    private final AuthenticationFacade facade;

    @PostMapping
    public TokenDTO authenticate(@RequestBody AuthenticationDTO authenticationDTO) {
        return facade.authenticate(authenticationDTO);
    }

}
