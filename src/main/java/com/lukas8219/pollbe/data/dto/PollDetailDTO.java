package com.lukas8219.pollbe.data.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class PollDetailDTO {

    private final PollCreatorDetailsDTO detail;
    private List<UserVoteDTO> against;
    private List<UserVoteDTO> favor;

}
