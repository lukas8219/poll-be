package com.lukas8219.pollbe.data.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PollCreatorDetailsDTO {

    private final String photo;
    private final String email;
    private final String name;
    private final Long id;

}
