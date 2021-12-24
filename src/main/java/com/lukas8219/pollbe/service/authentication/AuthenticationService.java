package com.lukas8219.pollbe.service.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    UserDetails loadUserByUsername(String username);

    Authentication authenticate(String email, String password);

}
