package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.dto.PollVoteDTO;
import com.lukas8219.pollbe.data.mapper.PollVoteMapper;
import com.lukas8219.pollbe.exception.AlreadyVotePollException;
import com.lukas8219.pollbe.exception.PollExpiredOrNotFoundException;
import com.lukas8219.pollbe.exception.UserNotFoundException;
import com.lukas8219.pollbe.repository.PollRepository;
import com.lukas8219.pollbe.repository.UserRepository;
import com.lukas8219.pollbe.repository.gateway.PollVoteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum.AGAINST;
import static com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum.FAVOR;

@Service
@RequiredArgsConstructor
public class PollVoteServiceImpl implements PollVoteService{

    private final PollRepository pollRepository;
    private final PollVoteGateway voteGateway;
    private final UserRepository userRepository;
    private final PollVoteMapper mapper;

    public PollVoteDTO vote(Long id, boolean approved, PollUserDetails userDetails) {
        var decision = approved ? FAVOR : AGAINST;
        if (!voteGateway.existsByPollIdAndVotedBy(id, userDetails.getId())) {
            var vote = new PollVote();
            vote.setDecision(decision);
            vote.setVotedAt(LocalDateTime.now());
            vote.setPoll(pollRepository.findByIdAndNotExpired(id).orElseThrow(PollExpiredOrNotFoundException::new));
            vote.setVotedBy(userRepository.findById(userDetails.getId()).orElseThrow(UserNotFoundException::new));
            return mapper.toDTO(voteGateway.save(vote));
        } else {
            throw new AlreadyVotePollException();
        }
    }

}