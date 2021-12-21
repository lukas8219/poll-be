package com.lukas8219.pollbe.data.decorator;

import com.lukas8219.pollbe.data.dto.PollResultDTO;
import com.lukas8219.pollbe.data.dto.PollResultQueryRow;
import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import com.lukas8219.pollbe.data.mapper.PollResultMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum.AGAINST;
import static com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum.FAVOR;

public abstract class PollResultDecorator implements PollResultMapper {

    @Autowired
    private PollResultMapper delegate;

    @Override
    public List<PollResultDTO> toDTO(List<PollResultQueryRow> result) {
        return result.stream()
                .collect(Collectors.groupingBy(PollResultQueryRow::getId, Collectors.toList()))
                .values()
                .stream()
                .map(this::toResultDTO)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<PollResultDTO> toResultDTO(List<PollResultQueryRow> rows) {
        var id = getId(rows);
        var result = getResult(rows);
        return toResultDTO(id, result, rows);
    }

    private List<PollResultDTO> toResultDTO(Long id, PollResultEnum result, List<PollResultQueryRow> rows) {
        return rows.stream()
                .map(row -> new PollResultDTO(
                        id,
                        result,
                        row.getVotedBy(),
                        row.getUserEmail()
                ))
                .collect(Collectors.toList());
    }

    private PollResultEnum getResult(List<PollResultQueryRow> rows) {
        var againstVotes = countVote(rows, AGAINST);
        var favor = countVote(rows, FAVOR);
        return PollResultEnum.calculate(againstVotes, favor);
    }

    private Long getId(List<PollResultQueryRow> rows) {
        return rows.stream().map(PollResultQueryRow::getId).findAny().orElse(null);
    }

    private long countVote(List<PollResultQueryRow> rows, VoteDecisionEnum vote) {
        return rows.stream()
                .map(PollResultQueryRow::getVoteDecision)
                .filter(decision -> decision == vote)
                .count();
    }
}
