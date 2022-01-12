package com.lukas8219.pollbe.service.authentication;

import com.lukas8219.pollbe.data.dto.AuthenticationDTO;
import com.lukas8219.pollbe.data.dto.TokenDTO;
import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {

    TokenDTO authenticate(AuthenticationDTO dto);

    TokenDTO authenticateByJwt(String jwt);

    Authentication authenticateByWebSocket(String jwt);

}
