package com.lukas8219.pollbe.service;

import com.lukas8219.pollbe.data.domain.AuthenticationProviderEnum;
import com.lukas8219.pollbe.data.domain.PollOAuth2User;
import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.lukas8219.pollbe.data.domain.AuthenticationProviderEnum.GOOGLE;

@Service
@RequiredArgsConstructor
public class UserCreateServiceImpl {

    private final UserRepository repository;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();
    private final String TEMPORARY_PASS = "1234";

    public User createOrFind(PollOAuth2User oAuth2User) {
        var user = repository.findByEmailAndProvider(oAuth2User.getEmail(), GOOGLE);
        if (user.isEmpty()) {
            var newUser = new User();
            newUser.setEmail(oAuth2User.getEmail());
            newUser.setName(oAuth2User.getName());
            newUser.setPassword(encoder.encode(TEMPORARY_PASS));
            newUser.setProvider(GOOGLE);
            newUser.setCreatedAt(LocalDateTime.now());
            return repository.save(newUser);
        } else {
            return user.get();
        }
    }
}
