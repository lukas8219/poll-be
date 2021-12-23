package com.lukas8219.pollbe.repository.gateway;

import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.exception.PollVoteNotFoundException;
import com.lukas8219.pollbe.repository.PollVoteRepository;
import com.lukas8219.pollbe.repository.dao.PollVoteDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PollVoteGatewayImpl implements PollVoteGateway {

    private final PollVoteRepository repository;
    private final PollVoteDao pollDao;


    @Override
    public PollVote save(PollVote poll) {
        return repository.save(poll);
    }

    @Override
    public PollVote findById(Long id) {
        return repository.findById(id).orElseThrow(PollVoteNotFoundException::new);
    }

    @Override
    public boolean existsByPollIdAndVotedBy(Long id, Long votedBy) {
        return pollDao.existsByPollIdAndVotedBy(id, votedBy);
    }

}
