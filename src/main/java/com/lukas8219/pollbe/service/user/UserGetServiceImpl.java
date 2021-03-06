package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.exception.MissingParametersException;
import com.lukas8219.pollbe.exception.UserNotFoundException;
import com.lukas8219.pollbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGetServiceImpl implements UserGetService {

    private final UserRepository repository;

    public User getUser(Long id) {
        if (id == null) {
            throw new MissingParametersException("id");
        }
        return repository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
