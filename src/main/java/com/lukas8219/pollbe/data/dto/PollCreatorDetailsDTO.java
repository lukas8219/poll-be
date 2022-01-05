package com.lukas8219.pollbe.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PollCreatorDetailsDTO {

    private String userPhoto;
    private String userEmail;
    private String userName;
    private Long userId;

    private String pollDescription;
    private String pollSubject;
    private LocalDateTime pollExpiresAt;
    private LocalDateTime pollReportedAt;
}
