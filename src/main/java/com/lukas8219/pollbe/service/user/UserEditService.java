package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.UserDTO;
import com.lukas8219.pollbe.data.dto.UserEditDTO;
import com.lukas8219.pollbe.data.mapper.UserMapper;
import com.lukas8219.pollbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEditService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserDTO edit(PollUserDetails userDetails, UserEditDTO dto) {
        var user = repository.findById(userDetails.getId()).orElseThrow(RuntimeException::new);
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPhoneNumber(dto.getPhoneNumber());

        repository.save(user);
        return mapper.toDTO(user);
    }
}
