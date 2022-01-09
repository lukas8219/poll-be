package com.lukas8219.pollbe.data.dto;

import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PollDTO {

    private Long id;
    private String subject;
    private String description;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;

    private List<UserVoteDTO> usersVotes;
    private Long favor;
    private Long against;

    private PollResultEnum result;
    private VoteDecisionEnum vote;

    private PollCreatorDetailsDTO creator;

}
