package com.lukas8219.pollbe.repository.gateway;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PollVoteGateway {

    @Transactional
    PollVote save(PollVote poll);

    PollVote findById(Long id);

    Poll findPollByIdAndNotExpired(Long id);

    User findVotingUserById(Long id);

    boolean existsByPollIdAndVotedBy(Long id, Long votedBy);
}
