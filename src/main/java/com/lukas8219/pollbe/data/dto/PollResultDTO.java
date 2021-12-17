package com.lukas8219.pollbe.data.dto;

import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PollResultDTO {

    private Long pollId;
    private PollResultEnum result;
    private List<UserVoteDecisionDTO> users;

}
