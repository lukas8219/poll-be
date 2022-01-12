package com.lukas8219.pollbe.data.domain;

import com.lukas8219.pollbe.exception.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AuthenticationProviderEnum {

    API("api"),
    GOOGLE("google");

    private final String clientName;

    public static AuthenticationProviderEnum parse(String clientName) {
        for(var provider : values()){
            if(provider.clientName.equalsIgnoreCase(clientName)){
                return provider;
            }
        }

        throw new UnprocessableEntityException("Provedor OAuth2.0 inválido");
    }
}
