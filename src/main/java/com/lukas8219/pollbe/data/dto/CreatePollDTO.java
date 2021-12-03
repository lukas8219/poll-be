package com.lukas8219.pollbe.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreatePollDTO {

    private String subject;
    private String description;
    private LocalDateTime expiresAt;

}
