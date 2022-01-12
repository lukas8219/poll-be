package com.lukas8219.pollbe.data.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
public class PollUserDetails extends User {

    private Long id;

    public PollUserDetails(com.lukas8219.pollbe.data.domain.User user){
        this(user.getId(), user.getEmail(), user.getPassword());
    }

    public PollUserDetails(Long id, String username, String password) {
        this(username, password, Collections.singleton(new SimpleGrantedAuthority("USER")));
        this.id = id;
    }

    public PollUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public PollUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
