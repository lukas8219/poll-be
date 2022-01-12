package com.lukas8219.pollbe.service.authentication.token;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.TokenDTO;

public interface TokenService {

    TokenDTO generateToken(PollUserDetails userDetails);

    String extractUsername(String jwt);
}
