package com.lukas8219.pollbe.data.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class PollResultListDTO {

    private final List<PollResultDTO> list;

}
