package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.data.dto.UserEditDTO;
import com.lukas8219.pollbe.exception.UserEmailAlreadyExists;
import com.lukas8219.pollbe.helper.builder.UserBuilder;
import com.lukas8219.pollbe.helper.factory.UserDetailsFactory;
import com.lukas8219.pollbe.helper.stub.UserRepositoryStub;
import com.lukas8219.pollbe.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class UserEditServiceImplTest {

    private UserRepository repository;
    private UserEditServiceImpl subject;
    private User user;

    @BeforeEach
    public void setup() {
        var users = new HashMap<Long, User>();
        user = UserBuilder.newBuilder(1L).build();
        users.put(user.getId(), user);
        repository = new UserRepositoryStub(users);
        subject = new UserEditServiceImpl(repository);
    }

    @Test
    public void whenUser_edit_mustEditCorrecFields() {
        final var email = "2";
        final var name = "Dev 2";
        final var number = "9999";

        var dto = new UserEditDTO();
        dto.setEmail(email);
        dto.setPhoneNumber(number);
        dto.setName(name);
        var user = subject.edit(UserDetailsFactory.of(this.user.getId(), "1"), dto);

        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getPhoneNumber()).isEqualTo(number);
    }

    @Test
    public void whenEditUser_withSomeFieldMissing_shouldNotNullifyTheOthers() {
        final var email = "2";

        var dto = new UserEditDTO();
        dto.setEmail(email);
        var user = subject.edit(UserDetailsFactory.of(1L, "1"), dto);

        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getName()).isEqualTo(user.getName());
        assertThat(user.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
    }

    @Test
    public void whenUserEdit_mustEdit_onlyTheRequestedUser() {
        repository.save(UserBuilder.newBuilder(2L).build());

        final var email = "2";
        final var name = "Dev 2";
        final var number = "9999";

        var dto = new UserEditDTO();
        dto.setEmail(email);
        dto.setPhoneNumber(number);
        dto.setName(name);
        var user = subject.edit(UserDetailsFactory.of(this.user.getId(), "1"), dto);

        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getPhoneNumber()).isEqualTo(number);

        var secondUser = repository.findById(2L).orElse(null);
        assertThat(secondUser).isNotNull();
        assertThat(secondUser.getEmail()).isNotEqualTo(email);
        assertThat(secondUser.getName()).isNotEqualTo(name);
        assertThat(secondUser.getPhoneNumber()).isNotEqualTo(number);

    }

    @Test
    public void whenEditUser_withDuplicateEmail_mustReturnError() {
        final var email = user.getEmail();
        final var name = "Dev 2";
        final var number = "9999";

        var dto = new UserEditDTO();
        dto.setEmail(email);
        dto.setPhoneNumber(number);
        dto.setName(name);

        Assertions.assertThrows(UserEmailAlreadyExists.class, () -> subject.edit(UserDetailsFactory.of(this.user.getId() + 1, this.user.getEmail()), dto));

    }

}