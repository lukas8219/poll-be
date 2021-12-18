package com.lukas8219.pollbe.data.mapper.decorator;


import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.dto.PollDTO;
import com.lukas8219.pollbe.data.dto.PollResultDTO;
import com.lukas8219.pollbe.data.dto.PollResultQueryRow;
import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import com.lukas8219.pollbe.data.mapper.PollMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum.ABSTENTION;

@Component
public abstract class PollDecorator implements PollMapper {

    @Autowired
    private PollMapper delegate;

    @Override
    public PollDTO toDTO(Poll poll, PollUserDetails userDetails) {
        var result = delegate.toDTO(poll, userDetails);
        result.setAgainst(countVotes(poll, VoteDecisionEnum.AGAINST));
        result.setFavor(countVotes(poll, VoteDecisionEnum.FAVOR));
        result.setResult(PollResultEnum.calculate(poll.getExpiresAt(), result.getAgainst(), result.getFavor()));
        result.setVote(getVoteDecision(poll, userDetails));
        return result;
    }

    private VoteDecisionEnum getVoteDecision(Poll poll, PollUserDetails userDetails) {
        return poll.getVotes()
                .stream()
                .filter(vote -> vote.getVotedBy().equals(userDetails.getId()))
                .map(PollVote::getDecision)
                .findFirst()
                .orElse(ABSTENTION);
    }

    private Long countVotes(Poll poll, VoteDecisionEnum decision) {
        if (!CollectionUtils.isEmpty(poll.getVotes())) {
            return poll.getVotes().stream()
                    .map(PollVote::getDecision)
                    .filter(vote -> vote == decision)
                    .count();
        } else {
            return 0L;
        }
    }

    @Override
    public List<PollResultDTO> toReportList(List<PollResultQueryRow> result) {
        return result.stream()
                .collect(Collectors.groupingBy(PollResultQueryRow::getId, Collectors.toList()))
                .values()
                .stream()
                .map(this::toResultDTO)
                .collect(Collectors.toList());
    }

    private PollResultDTO toResultDTO(List<PollResultQueryRow> rows) {
        var result = rows.stream()
                .max(Comparator.comparing(PollResultQueryRow::getCount))
                .map(PollResultQueryRow::getDecision)
                .map(this::toResultEnum)
                .orElse(null);
        var pollId = rows.stream()
                .map(PollResultQueryRow::getId)
                .findAny()
                .orElse(null);
        return new PollResultDTO(pollId, result);
    }

    private PollResultEnum toResultEnum(VoteDecisionEnum voteDecisionEnum) {
        if (voteDecisionEnum == VoteDecisionEnum.FAVOR) {
            return PollResultEnum.APPROVED;
        } else if (voteDecisionEnum == VoteDecisionEnum.AGAINST) {
            return PollResultEnum.REFUSED;
        } else {
            return PollResultEnum.TIE;
        }
    }

    @Override
    public PollResultDTO toFinal(PollResultDTO x, List<Long> usersThatVoted) {
        x.setUsers(usersThatVoted);
        return x;
    }
}
