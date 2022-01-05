package com.lukas8219.pollbe.data.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PollCreatorDetailsDTO {

    private final String userPhoto;
    private final String userEmail;
    private final String userName;
    private final Long userId;

}
