package com.lukas8219.pollbe.data.dto;

import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class PollResultDTO {

    private final Long pollId;
    private final PollResultEnum result;
    private List<Long> users;

}
