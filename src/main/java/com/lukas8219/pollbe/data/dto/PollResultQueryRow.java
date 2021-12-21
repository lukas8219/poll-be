package com.lukas8219.pollbe.data.dto;

import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PollResultQueryRow {

    private Long id;
    private VoteDecisionEnum voteDecision;
    private Long votedBy;
    private String userEmail;

}
