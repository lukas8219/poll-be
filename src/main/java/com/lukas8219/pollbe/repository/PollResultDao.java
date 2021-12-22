package com.lukas8219.pollbe.repository;

import com.lukas8219.pollbe.data.dto.PollResultDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PollResultDao {

    List<PollResultDTO> getAllFinishedPolls();

    @Transactional
    void updateReportedAtForPolls(List<Long> collect);
}
