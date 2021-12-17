package com.lukas8219.pollbe.data.dto;

import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVoteDecisionDTO {

    private Long id;
    private VoteDecisionEnum decision;

}
