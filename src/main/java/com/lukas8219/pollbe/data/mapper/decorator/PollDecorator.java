package com.lukas8219.pollbe.data.mapper.decorator;


import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.dto.PollDTO;
import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import com.lukas8219.pollbe.data.mapper.PollMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public abstract class PollDecorator implements PollMapper {

    @Autowired
    private PollMapper delegate;

    @Override
    public PollDTO toDTO(Poll poll) {
        var result = delegate.toDTO(poll);
        result.setAgainst(countVotes(poll, VoteDecisionEnum.AGAINST));
        result.setFavor(countVotes(poll, VoteDecisionEnum.FAVOR));
        result.setResult(PollResultEnum.calculate(poll.getExpiresAt(), result.getAgainst(), result.getFavor()));
        return result;
    }

    private Long countVotes(Poll poll, VoteDecisionEnum decision) {
        if(!CollectionUtils.isEmpty(poll.getVotes())){
            return poll.getVotes().stream()
                    .map(PollVote::getDecision)
                    .filter(vote -> vote == decision)
                    .count();
        } else {
            return 0L;
        }
    }
}
