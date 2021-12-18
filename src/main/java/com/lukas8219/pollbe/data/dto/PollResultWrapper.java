package com.lukas8219.pollbe.data.dto;

import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class PollResultWrapper {

    private final PollResultEnum result;
    private final Long id;

    public PollResultWrapper(List<PollResultQueryRow> rows) {
        this.result = rows.stream()
                .max(Comparator.comparing(PollResultQueryRow::getCount))
                .map(PollResultQueryRow::getDecision)
                .map(this::toResultEnum)
                .orElse(null);
        this.id = rows.stream()
                .map(PollResultQueryRow::getId)
                .findAny()
                .orElse(null);
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
}
