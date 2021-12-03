package com.lukas8219.pollbe.data.dto;

import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PollDTO {

    private String subject;
    private String description;
    private Long favor;
    private Long against;
    private LocalDateTime expiresAt;
    private PollResultEnum result;

}
