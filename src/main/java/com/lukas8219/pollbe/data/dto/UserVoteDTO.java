package com.lukas8219.pollbe.data.dto;

import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserVoteDTO {

    private final Long id;
    private final String photo;
    private final String email;
    private final String name;
    private final VoteDecisionEnum vote;

}
