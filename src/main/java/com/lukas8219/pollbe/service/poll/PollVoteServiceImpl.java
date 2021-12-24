package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.exception.AlreadyVotePollException;
import com.lukas8219.pollbe.repository.gateway.PollVoteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum.AGAINST;
import static com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum.FAVOR;

@Service
@RequiredArgsConstructor
public class PollVoteServiceImpl implements PollVoteService {

    private final PollVoteGateway voteGateway;

    public PollVote vote(Long id, boolean approved, PollUserDetails userDetails) {
        var decision = approved ? FAVOR : AGAINST;
        if (!voteGateway.existsByPollIdAndVotedBy(id, userDetails.getId())) {
            var vote = new PollVote();
            vote.setDecision(decision);
            vote.setVotedAt(LocalDateTime.now());
            vote.setPoll(voteGateway.findPollByIdAndNotExpired(id));
            vote.setVotedBy(voteGateway.findVotingUserById(userDetails.getId()));
            return voteGateway.save(vote);
        } else {
            throw new AlreadyVotePollException();
        }
    }

}
