package com.lukas8219.pollbe.data.dto;

import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PollVoteDTO {

    private VoteDecisionEnum decision;
    private LocalDateTime votedAt;
    private Long poll;

}
