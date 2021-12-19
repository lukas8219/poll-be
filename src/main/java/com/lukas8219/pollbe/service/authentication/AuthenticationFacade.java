package com.lukas8219.pollbe.service.authentication;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.AuthenticationDTO;
import com.lukas8219.pollbe.data.dto.TokenDTO;
import com.lukas8219.pollbe.exception.UserInvalidException;
import com.lukas8219.pollbe.security.JwtUtil;
import com.lukas8219.pollbe.security.PollUserDetailsService;
import com.lukas8219.pollbe.service.user.UserGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationFacade {

    private final AuthenticationManager authenticationManager;
    private final PollUserDetailsService userDetailsService;
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

    private Authentication authenticate(String email, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    public Authentication authenticateByWebSocket(String jwt) {
        var username = jwtUtil.extractUsername(jwt);
        var user = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
}
