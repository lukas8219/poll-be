package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.CreatePollDTO;
import com.lukas8219.pollbe.data.mapper.PollMapper;
import com.lukas8219.pollbe.data.mapper.PollMapperImpl;
import com.lukas8219.pollbe.data.mapper.PollMapperImpl_;
import com.lukas8219.pollbe.helper.builder.UserBuilder;
import com.lukas8219.pollbe.helper.factory.UserDetailsFactory;
import com.lukas8219.pollbe.helper.stub.PollRepositoryStub;
import com.lukas8219.pollbe.helper.stub.UserRepositoryStub;
import com.lukas8219.pollbe.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


class PollCreateServiceImplTest {

    private final String DEFAULT_DESCRIPTION = "1";
    private final String DEFAULT_SUBJECT = "1";
    private final PollUserDetails USER = UserDetailsFactory.of(1L, "1@email.com");

    private PollCreateServiceImpl subject;
    private PollRepositoryStub repositoryStub;
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        repositoryStub = new PollRepositoryStub();
        userRepository = new UserRepositoryStub();
        userRepository.save(UserBuilder.newBuilder(USER.getId()).build());
        subject = new PollCreateServiceImpl(repositoryStub, userRepository);
    }

    public CreatePollDTO create(LocalDateTime expires) {
        var result = new CreatePollDTO();
        result.setDescription(DEFAULT_DESCRIPTION);
        result.setSubject(DEFAULT_SUBJECT);
        if (expires == null) {
            expires = LocalDateTime.now();
        }
        result.setExpiresAt(expires);
        return result;
    }

    public CreatePollDTO create() {
        return create(null);
    }

    @Test
    public void whenCreate_mustReturn_allFieldsValues() {
        var expiresAt = LocalDateTime.now().plusMinutes(1L);
        var result = subject.create(create(expiresAt), USER);

        assertThat(result.getDescription()).isNotNull();
        assertThat(result.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);

        Assertions.assertAll(() -> {
            assertThat(result).isNotNull();

            assertThat(result.getDescription()).isNotNull();
            assertThat(result.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);

            assertThat(result.getSubject()).isNotNull();
            assertThat(result.getSubject()).isEqualTo(DEFAULT_SUBJECT);

            assertThat(result.getExpiresAt()).isNotNull();
            assertThat(result.getExpiresAt()).isEqualTo(expiresAt);

            assertThat(result.getCreatedBy()).isNotNull();
            assertThat(result.getCreatedBy().getId()).isEqualTo(USER.getId());
        });
    }

    @Test
    public void whenCreate_mustPersist() {
        var dto = subject.create(create(), USER);
        var result = repositoryStub.findById(dto.getId()).orElse(null);
        assertThat(result).isNotNull();
    }

}