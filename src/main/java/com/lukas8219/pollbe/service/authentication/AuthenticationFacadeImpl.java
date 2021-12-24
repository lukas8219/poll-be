package com.lukas8219.pollbe.service.authentication;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.AuthenticationDTO;
import com.lukas8219.pollbe.data.dto.TokenDTO;
import com.lukas8219.pollbe.exception.UserInvalidException;
import com.lukas8219.pollbe.service.authentication.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    private final AuthenticationService authenticationService;
    private final TokenService tokenService;

    public TokenDTO authenticate(AuthenticationDTO dto) {
        var result = getPrincipal(dto);
        return tokenService.generateToken(result);
    }

    private PollUserDetails getPrincipal(AuthenticationDTO dto) {
        try {
            return (PollUserDetails) authenticationService.authenticate(dto.getEmail(), dto.getPassword()).getPrincipal();
        } catch (BadCredentialsException e) {
            throw new UserInvalidException();
        }

    }

    public Authentication authenticateByWebSocket(String jwt) {
        var username = tokenService.extractUsername(jwt);
        var user = authenticationService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
}
