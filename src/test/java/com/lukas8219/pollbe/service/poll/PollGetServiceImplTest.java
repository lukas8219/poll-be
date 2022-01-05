package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.config.ApplicationConfiguration;
import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import com.lukas8219.pollbe.data.mapper.PollMapper;
import com.lukas8219.pollbe.data.mapper.PollMapperImpl;
import com.lukas8219.pollbe.data.mapper.PollMapperImpl_;
import com.lukas8219.pollbe.exception.PollNotFoundException;
import com.lukas8219.pollbe.helper.builder.PollBuilder;
import com.lukas8219.pollbe.helper.builder.PollVoteBuilder;
import com.lukas8219.pollbe.helper.builder.UserBuilder;
import com.lukas8219.pollbe.helper.factory.UserDetailsFactory;
import com.lukas8219.pollbe.helper.stub.PollDetailDAOStub;
import com.lukas8219.pollbe.helper.stub.PollRepositoryStub;
import com.lukas8219.pollbe.repository.PollDetailDAO;
import com.lukas8219.pollbe.repository.PollRepository;
import com.lukas8219.pollbe.service.LocalFileStorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@Import({
        PollMapperImpl.class,
        PollMapperImpl_.class,
        LocalFileStorageService.class,
        ApplicationConfiguration.class
})
class PollGetServiceImplTest {

    private final PollUserDetails USER = UserDetailsFactory.of(1L, "1");
    private final User POLL_USER = UserBuilder.newBuilder(USER.getId()).createdAt(LocalDateTime.now()).build();

    private final Map<Long, User> users = Map.of(1L, POLL_USER,
            2L, UserBuilder.newBuilder(2L).email("2").name("2").build(),
            3L, UserBuilder.newBuilder(2L).email("3").name("3").build());

    private PollGetServiceImpl subject;
    private PollGateway pollGateway;
    private PollRepository repository;
    private PollDetailDAO detailDAO;

    @Autowired
    private PollMapper pollMapper;

    @BeforeEach
    public void setup() {

        repository = new PollRepositoryStub();
        detailDAO = new PollDetailDAOStub(repository, pollMapper);

        pollGateway = new PollGatewayImpl(repository, detailDAO);

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

    public Poll createEntirePoll() {

        var poll = PollBuilder.newBuilder(POLL_USER)
                .addVote(PollVoteBuilder.of(null, VoteDecisionEnum.FAVOR, POLL_USER))
                .addVote(PollVoteBuilder.of(null, VoteDecisionEnum.AGAINST, users.get(2L)))
                .addVote(PollVoteBuilder.of(null, VoteDecisionEnum.AGAINST, users.get(3L)))
                .reportedAt(LocalDateTime.now())
                .build(LocalDateTime.now());

        return repository.save(poll);
    }

    @Test
    public void whenGetByExistingShouldReturn() {
        var poll = createPoll();
        var result = subject.get(poll.getId(), USER);
        assertThat(result).isNotNull();
    }


    @Test
    public void whenGetByUnexistentShouldReturnProperException() {
        Assertions.assertThrows(PollNotFoundException.class, () -> subject.get(990L, USER));
    }

    @Test
    public void whenGetByDetails_shouldReturnProperObject() {
        var poll = createEntirePoll();
        var result = subject.getDetails(poll.getId(), USER);
        assertThat(result).isNotNull();

        assertThat(result.getDetail()).isNotNull();
        assertThat(result.getFavor()).isNotNull();
        assertThat(result.getAgainst()).isNotNull();

        assertThat(result.getAgainst().size()).isEqualTo(2);
        assertThat(result.getFavor().size()).isEqualTo(1);

        assertThat(result.getAgainst().stream().anyMatch(x -> Objects.equals(x.getId(), users.get(3L).getId()))).isTrue();
        assertThat(result.getAgainst().stream().anyMatch(x -> Objects.equals(x.getId(), users.get(2L).getId()))).isTrue();
        assertThat(result.getFavor().stream().anyMatch(x -> Objects.equals(x.getId(), users.get(1L).getId()))).isTrue();

        assertThat(result.getFavor().stream().anyMatch(x -> Objects.equals(x.getId(), users.get(3L).getId()))).isFalse();
        assertThat(result.getFavor().stream().anyMatch(x -> Objects.equals(x.getId(), users.get(2L).getId()))).isFalse();
        assertThat(result.getAgainst().stream().anyMatch(x -> Objects.equals(x.getId(), users.get(1L).getId()))).isFalse();

        var details = result.getDetail();

        assertThat(details.getPollDescription()).isEqualTo(poll.getDescription());
        assertThat(details.getPollSubject()).isEqualTo(poll.getSubject());
        assertThat(details.getPollReportedAt()).isEqualTo(poll.getReportedAt());
        assertThat(details.getPollExpiresAt()).isEqualTo(poll.getExpiresAt());

        assertThat(details.getUserEmail()).isEqualTo(POLL_USER.getEmail());
        assertThat(details.getUserId()).isEqualTo(POLL_USER.getId());
        assertThat(details.getUserName()).isEqualTo(POLL_USER.getName());
        assertThat(details.getUserPhoto()).isNull();
    }

}