package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.UserDTO;
import com.lukas8219.pollbe.data.mapper.UserMapper;
import com.lukas8219.pollbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGetService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserDTO getUser(PollUserDetails userDetails) {
        return repository.findById(userDetails.getId())
                .map(mapper::toDTO)
                .orElseThrow(RuntimeException::new);
    }
}
