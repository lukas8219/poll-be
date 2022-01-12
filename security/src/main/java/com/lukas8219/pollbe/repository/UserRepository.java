package com.lukas8219.pollbe.repository;

import com.lukas8219.pollbe.data.domain.AuthenticationProviderEnum;
import com.lukas8219.pollbe.data.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String username);

    Optional<User> findByEmailAndProvider(String email, AuthenticationProviderEnum provider);

    @Query("SELECT COUNT(id)>0 FROM User WHERE email = :email AND id !=  :id")
    boolean emailAlreadyExists(String email, Long id);

    @Query("FROM User WHERE registrationId = :registrationId AND provider != 'API' ")
    Optional<User> findByRegistrationId(String registrationId);
}
