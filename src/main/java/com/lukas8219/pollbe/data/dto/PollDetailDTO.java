package com.lukas8219.pollbe.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PollDetailDTO {

    private PollCreatorDetailsDTO detail;
    private List<UserVoteDTO> against;
    private List<UserVoteDTO> favor;

}
