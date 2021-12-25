package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.exception.MissingParametersException;
import com.lukas8219.pollbe.exception.UserNotFoundException;
import com.lukas8219.pollbe.helper.builder.UserBuilder;
import com.lukas8219.pollbe.helper.stub.UserRepositoryStub;
import com.lukas8219.pollbe.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserGetServiceImplTest {

    private UserRepository repository;
    private UserGetServiceImpl subject;
    private User user;


    @BeforeEach
    public void setup() {
        user = UserBuilder.newBuilder(1L).build();
        repository = new UserRepositoryStub(Map.of(user.getId(), user));
        subject = new UserGetServiceImpl(repository);
    }


    @Test
    public void whenGetById_shouldReturnProperUser() {
        var result = subject.getUser(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(user.getName());
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
        assertThat(result.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
        assertThat(result.getCreatedAt()).isEqualTo(user.getCreatedAt());
        assertThat(result.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    public void whenGetById_should_returnProperException() {
        Assertions.assertThrows(UserNotFoundException.class, () -> subject.getUser(2L));
    }

    @Test
    public void whenGetById_withNull_shouldReturnProperError() {
        Assertions.assertThrows(MissingParametersException.class, () -> subject.getUser(null));
    }
}