package com.lukas8219.pollbe.repository.gateway;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.exception.PollExpiredOrNotFoundException;
import com.lukas8219.pollbe.exception.PollVoteNotFoundException;
import com.lukas8219.pollbe.exception.UserNotFoundException;
import com.lukas8219.pollbe.repository.PollRepository;
import com.lukas8219.pollbe.repository.PollVoteRepository;
import com.lukas8219.pollbe.repository.UserRepository;
import com.lukas8219.pollbe.repository.dao.PollVoteDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PollVoteGatewayImpl implements PollVoteGateway {

    private final PollVoteRepository repository;
    private final PollRepository pollRepository;
    private final PollVoteDao pollDao;
    private final UserRepository userRepository;


    @Override
    public PollVote save(PollVote poll) {
        return repository.save(poll);
    }

    @Override
    public PollVote findById(Long id) {
        return repository.findById(id).orElseThrow(PollVoteNotFoundException::new);
    }

    @Override
    public Poll findPollByIdAndNotExpired(Long id) {
        return pollRepository.findByIdAndNotExpired(id).orElseThrow(PollExpiredOrNotFoundException::new);
    }

    @Override
    public User findVotingUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public boolean existsByPollIdAndVotedBy(Long id, Long votedBy) {
        return pollDao.existsByPollIdAndVotedBy(id, votedBy);
    }

}
