package com.lukas8219.pollbe.repository;

import com.lukas8219.pollbe.data.dto.PollResultDTO;

import java.util.List;

public interface PollResultDao {

    List<PollResultDTO> getAllFinishedPolls();

}
