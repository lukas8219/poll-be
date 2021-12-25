package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.data.dto.UserEditDTO;
import com.lukas8219.pollbe.exception.UserEmailAlreadyExists;
import com.lukas8219.pollbe.exception.UserNotFoundException;
import com.lukas8219.pollbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEditServiceImpl implements UserEditService {

    private final UserRepository repository;

    public User edit(PollUserDetails userDetails, UserEditDTO dto) {
        if (dto.getEmail() != null && repository.emailAlreadyExists(dto.getEmail(), userDetails.getId())) {
            throw new UserEmailAlreadyExists();
        }

        var user = repository.findById(userDetails.getId()).orElseThrow(UserNotFoundException::new);

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getName() != null) {
            user.setName(dto.getName());
        }

        if (dto.getPhoneNumber() != null) {
            user.setPhoneNumber(dto.getPhoneNumber());
        }
        return repository.save(user);
    }

}
