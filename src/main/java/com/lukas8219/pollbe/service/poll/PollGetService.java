package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.dto.PollDTO;
import com.lukas8219.pollbe.data.mapper.PollMapper;
import com.lukas8219.pollbe.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PollGetService {

    private final PollRepository repository;
    private final PollMapper mapper;

    public PollDTO get(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(RuntimeException::new);
    }
}
