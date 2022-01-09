package com.lukas8219.pollbe.data.dto;

import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollListDTO {

    private Long id;
    private String subject;
    private VoteDecisionEnum vote;
    private PollResultEnum result;

}
