package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import com.lukas8219.pollbe.exception.AlreadyVotePollException;
import com.lukas8219.pollbe.exception.PollExpiredOrNotFoundException;
import com.lukas8219.pollbe.helper.factory.UserDetailsFactory;
import com.lukas8219.pollbe.repository.gateway.PollVoteGatewayImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

class PollVoteServiceImplTest {

    private final PollUserDetails USER = UserDetailsFactory.of(1L, "1@email");
    private PollVoteServiceImpl subject;

    @Mock
    private PollVoteGatewayImpl voteGateway;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        subject = new PollVoteServiceImpl(voteGateway);
    }

    @Test
    public void whenVoteForExpiredPoll_shoudReturnProperException() {
        when(voteGateway.existsByPollIdAndVotedBy(any(), any())).thenReturn(false);
        when(voteGateway.findPollByIdAndNotExpired(anyLong())).thenThrow(PollExpiredOrNotFoundException.class);
        when(voteGateway.findVotingUserById(anyLong())).thenReturn(new User());
        Assertions.assertThrows(PollExpiredOrNotFoundException.class, () -> subject.vote(1L, true, USER));
    }

    @ParameterizedTest
    @CsvSource({
            "false, AGAINST",
            "true, FAVOR"
    })
    public void whenVote_MustVoteToProperVoteDecision(boolean decision, VoteDecisionEnum result) {
        var poll = new Poll();
        poll.setId(1L);

        var user = new User();
        user.setId(1L);

        when(voteGateway.existsByPollIdAndVotedBy(any(), any())).thenReturn(false);
        when(voteGateway.findPollByIdAndNotExpired(anyLong())).thenReturn(poll);
        when(voteGateway.findVotingUserById(anyLong())).thenReturn(user);
        when(voteGateway.save(any())).thenAnswer(inv -> inv.getArgument(0));

        var dto = subject.vote(1L, decision, USER);


        Assertions.assertAll(() -> {
            assertThat(dto).isNotNull();
            assertThat(dto.getPoll()).isNotNull();

            assertThat(dto.getDecision()).isNotNull();
            assertThat(dto.getDecision()).isEqualTo(result);

            assertThat(dto.getVotedAt()).isNotNull();
            assertThat(dto.getVotedAt()).isBetween(LocalDateTime.now().minusSeconds(1L), LocalDateTime.now());
        });
    }

    @Test
    public void whenAlreadyVoted_shouldReturnProperException() {
        when(voteGateway.existsByPollIdAndVotedBy(any(), any())).thenReturn(true);
        Assertions.assertThrows(AlreadyVotePollException.class, () -> subject.vote(1L, true, USER));
    }


}