package com.lukas8219.pollbe.security;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Primary
@RequiredArgsConstructor
public class PollUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .map(this::toUserDetail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    private UserDetails toUserDetail(User user) {
        return new PollUserDetails(user.getId(), user.getEmail(), user.getPassword());
    }

}
