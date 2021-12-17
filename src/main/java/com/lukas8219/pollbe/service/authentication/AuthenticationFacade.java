package com.lukas8219.pollbe.service.authentication;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.AuthenticationDTO;
import com.lukas8219.pollbe.data.dto.TokenDTO;
import com.lukas8219.pollbe.exception.UserInvalidException;
import com.lukas8219.pollbe.security.JwtUtil;
import com.lukas8219.pollbe.service.user.UserGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationFacade {

    private final AuthenticationManager authenticationManager;
    private final UserGetService userGetService;
    private final JwtUtil jwtUtil;

    public TokenDTO authenticate(AuthenticationDTO dto) {
        var result = getPrincipal(dto);
        var token = jwtUtil.generateToken(result);
        var user = userGetService.getUser(result);
        return new TokenDTO(user, token);
    }

    private PollUserDetails getPrincipal(AuthenticationDTO dto) {
        try {
            return (PollUserDetails) authenticate(dto.getEmail(), dto.getPassword()).getPrincipal();
        } catch (BadCredentialsException e) {
            throw new UserInvalidException();
        }

    }

    public Authentication authenticate(String email, String password){
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }
}
