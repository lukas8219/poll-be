package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.dto.PollDetailDTO;

import java.util.List;
import java.util.Optional;

public interface PollGateway {

    Optional<Poll> findByIdAndNotExpired(Long id);

    List<Poll> findAll();

    Optional<PollDetailDTO> getDetails(Long id);
}
