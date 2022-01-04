package com.lukas8219.pollbe.data.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class PollCreatorDetailsDTO {

    private final String userPhoto;
    private final String userEmail;
    private final String userName;
    private final Long userId;

    private final String pollDescription;
    private final String pollSubject;
    private final LocalDateTime pollExpiresAt;
    private final LocalDateTime pollReportedAt;
}
