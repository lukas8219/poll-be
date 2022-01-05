package com.lukas8219.pollbe.repository;

import com.lukas8219.pollbe.data.dto.PollDetailDTO;

import java.util.Optional;

public interface PollDetailDAO {

    Optional<PollDetailDTO> getDetails(Long id);
}
