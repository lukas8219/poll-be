package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.config.ApplicationConfiguration;
import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import com.lukas8219.pollbe.data.mapper.PollMapperImpl;
import com.lukas8219.pollbe.data.mapper.PollMapperImpl_;
import com.lukas8219.pollbe.exception.PollNotFoundException;
import com.lukas8219.pollbe.helper.builder.PollBuilder;
import com.lukas8219.pollbe.helper.builder.PollVoteBuilder;
import com.lukas8219.pollbe.helper.builder.UserBuilder;
import com.lukas8219.pollbe.helper.factory.UserDetailsFactory;
import com.lukas8219.pollbe.helper.stub.PollRepositoryStub;
import com.lukas8219.pollbe.repository.PollRepository;
import com.lukas8219.pollbe.service.LocalFileStorageService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum.AGAINST;
import static com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum.FAVOR;
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
    private PollRepository repository;

    @BeforeEach
    public void setup() {

        repository = new PollRepositoryStub();

        subject = new PollGetServiceImpl(repository);

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
                .addVote(PollVoteBuilder.of(null, FAVOR, POLL_USER))
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
    public void whenGet_shouldReturnTheProperObject() {
        var poll = createEntirePoll();
        var result = subject.get(poll.getId(), USER);
        assertThat(result).isNotNull();

        assertThat(result).isNotNull();
        assertThat(result.getVotes()).isNotNull();
        assertThat(result.getCreatedBy()).isNotNull();

        assertThat(result.getVotes().size()).isEqualTo(3);

        assertThat(getFilteredByVote(result, FAVOR).size()).isEqualTo(1L);
        assertThat(getFilteredByVote(result, AGAINST).size()).isEqualTo(2L);

        assertThat(filteredVoteHasUserId(result, FAVOR, 1L)).isTrue();
        assertThat(filteredVoteHasUserId(result, AGAINST, 2L)).isTrue();
        assertThat(filteredVoteHasUserId(result, AGAINST, 3L)).isTrue();


        assertThat(filteredVoteHasUserId(result, AGAINST, 1L)).isFalse();
        assertThat(filteredVoteHasUserId(result, FAVOR, 2L)).isFalse();
        assertThat(filteredVoteHasUserId(result, FAVOR, 3L)).isFalse();


        assertThat(result.getDescription()).isEqualTo(poll.getDescription());
        assertThat(result.getSubject()).isEqualTo(poll.getSubject());
        assertThat(result.getReportedAt()).isEqualTo(poll.getReportedAt());
        assertThat(result.getExpiresAt()).isEqualTo(poll.getExpiresAt());

        assertThat(result.getCreatedBy().getEmail()).isEqualTo(POLL_USER.getEmail());
        assertThat(result.getCreatedBy().getId()).isEqualTo(POLL_USER.getId());
        assertThat(result.getCreatedBy().getName()).isEqualTo(POLL_USER.getName());
        assertThat(result.getCreatedBy().getPhoto()).isNull();
    }

    private boolean filteredVoteHasUserId(Poll result, VoteDecisionEnum vote, Long id) {
        return getFilteredByVote(result, vote).stream()
                .map(PollVote::getVotedBy)
                .anyMatch(x -> Objects.equals(x.getId(), users.get(id).getId()));
    }

    @NotNull
    private List<PollVote> getFilteredByVote(Poll result, VoteDecisionEnum vote) {
        return result.getVotes().stream().filter(x -> x.getDecision() == vote).collect(Collectors.toList());
    }

}