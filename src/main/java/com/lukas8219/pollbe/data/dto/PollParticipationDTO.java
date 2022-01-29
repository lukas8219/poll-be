package com.lukas8219.pollbe.data.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PollParticipationDTO {

    private Long id;
    private PollCreatorDetailsDTO creator;
    private String subject;
    private LocalDateTime votedAt;
    private Integer favor;
    private Integer against;
    private VoteDecisionEnum decision;
    private PollResultEnum result;

}
