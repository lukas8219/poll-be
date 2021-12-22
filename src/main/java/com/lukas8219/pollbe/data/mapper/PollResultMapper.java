package com.lukas8219.pollbe.data.mapper;

import com.lukas8219.pollbe.data.dto.PollResultDTO;
import com.lukas8219.pollbe.data.dto.PollResultQueryRow;

import java.util.List;

public interface PollResultMapper {

    List<PollResultDTO> toDTO(List<PollResultQueryRow> result);
}
