package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.exception.PollExpiredOrNotFoundException;
import com.lukas8219.pollbe.helper.factory.UserDetailsFactory;
import com.lukas8219.pollbe.helper.stub.PollRepositoryStub;
import com.lukas8219.pollbe.repository.PollRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
class PollGetServiceImplTest {

    private final PollUserDetails USER = UserDetailsFactory.of(1L, "1");
    private PollGetServiceImpl subject;
    private PollGateway pollGateway;
    private PollRepository repository;

    @BeforeEach
    public void setup() {
        repository = new PollRepositoryStub();
        subject = new PollGetServiceImpl(pollGateway);
    }

    public Poll createPoll() {
        return createPoll(null);
    }

    public Poll createPoll(LocalDateTime expires) {
        var poll = new Poll();
        poll.setExpiresAt(expires == null ? LocalDateTime.now().plusMinutes(1L) : expires);
        return repository.save(poll);
    }

    @Test
    public void whenGetByExistingShouldReturn() {
        var poll = createPoll();
        var result = subject.get(poll.getId(), USER);
        assertThat(result).isNotNull();
    }

    @Test
    public void whenGetByExpiredShouldReturnProperException() {
        var poll = createPoll(LocalDateTime.now().minusDays(1L));
        Assertions.assertThrows(PollExpiredOrNotFoundException.class, () -> subject.get(poll.getId(), USER));
    }

    @Test
    public void whenGetByUnexistentShouldReturnProperException() {
        Assertions.assertThrows(PollExpiredOrNotFoundException.class, () -> subject.get(990L, USER));
    }

}