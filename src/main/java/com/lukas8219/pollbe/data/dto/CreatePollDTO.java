package com.lukas8219.pollbe.data.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreatePollDTO {

    @NotEmpty(message = "{poll.created.subject.notEmpty}")
    private String subject;
    @NotEmpty(message = "{poll.created.description.notEmpty}")
    private String description;
    @NotNull(message = "{poll.created.expiresAt.notNull}")
    private LocalDateTime expiresAt;

}
