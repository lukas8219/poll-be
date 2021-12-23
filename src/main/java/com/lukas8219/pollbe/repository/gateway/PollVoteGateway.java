package com.lukas8219.pollbe.repository.gateway;

import com.lukas8219.pollbe.data.domain.PollVote;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PollVoteGateway {

    @Transactional
    PollVote save(PollVote poll);

    PollVote findById(Long id);

    boolean existsByPollIdAndVotedBy(Long id, Long votedBy);
}
