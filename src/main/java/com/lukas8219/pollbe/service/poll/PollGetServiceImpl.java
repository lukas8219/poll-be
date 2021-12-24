package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.PollDTO;
import com.lukas8219.pollbe.data.mapper.PollMapper;
import com.lukas8219.pollbe.exception.PollExpiredOrNotFoundException;
import com.lukas8219.pollbe.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PollGetServiceImpl implements PollGetService {

    private final PollRepository repository;
    private final PollMapper mapper;

    public PollDTO get(Long id, PollUserDetails userDetails) {
        return repository.findByIdAndNotExpired(id)
                .map(poll -> mapper.toDTO(poll, userDetails))
                .orElseThrow(PollExpiredOrNotFoundException::new);
    }

    public List<PollDTO> getAll(PollUserDetails userDetails) {
        return repository.findAll()
                .stream()
                .map(poll -> mapper.toDTO(poll, userDetails))
                .collect(Collectors.toList());
    }
}
