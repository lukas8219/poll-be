package com.lukas8219.pollbe.service;

import com.lukas8219.pollbe.data.domain.AuthenticationProviderEnum;
import com.lukas8219.pollbe.data.domain.PollOAuth2User;
import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserCreateServiceImpl {

    private final UserRepository repository;
    private final String TEMPORARY_PASS = "1234";
    private final ApplicationEventPublisher publisher;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    public User createOrFind(PollOAuth2User oAuth2User) {
        var user = repository.findByEmail(oAuth2User.getEmail());
        if (user.isEmpty()) {
            return create(oAuth2User.getEmail(), oAuth2User.getName(), null, oAuth2User.getProvider());
        } else {
            return user.get();
        }
    }

    public User create(String email, String name, String phoneNumber, AuthenticationProviderEnum provider) {
        var newUser = new User();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setPassword(encoder.encode(TEMPORARY_PASS));
        newUser.setProvider(provider);
        newUser.setCreatedAt(LocalDateTime.now());
        return repository.save(newUser);
    }
}
