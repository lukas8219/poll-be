package com.lukas8219.pollbe.data.dto;

import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PollResultDTO {

    private Long pollId;
    private PollResultEnum result;
    private Long user;
    private String email;

}
