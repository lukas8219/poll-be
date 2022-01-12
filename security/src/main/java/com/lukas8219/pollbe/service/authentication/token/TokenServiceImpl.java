package com.lukas8219.pollbe.service.authentication.token;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.TokenDTO;
import com.lukas8219.pollbe.data.mapper.UserMapper;
import com.lukas8219.pollbe.exception.UserNotFoundException;
import com.lukas8219.pollbe.repository.UserRepository;
import com.lukas8219.pollbe.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public TokenDTO generateToken(PollUserDetails userDetails) {
        var token = jwtUtil.generateToken(userDetails);
        var user = userRepository.findById(userDetails.getId()).orElseThrow(UserNotFoundException::new);
        return new TokenDTO(userMapper.toDTO(user), token);
    }

    @Override
    public String extractUsername(String jwt) {
        return jwtUtil.extractUsername(jwt);
    }
}
