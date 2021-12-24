package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.CreatePollDTO;
import com.lukas8219.pollbe.data.dto.PollDTO;
import com.lukas8219.pollbe.data.mapper.PollMapper;
import com.lukas8219.pollbe.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PollCreateServiceImpl implements PollCreateService {

    private final PollRepository repository;
    private final PollMapper mapper;

    public PollDTO create(CreatePollDTO dto, PollUserDetails userDetails) {
        var poll = mapper.toPoll(dto);
        poll.setCreatedBy(userDetails.getId());
        repository.save(poll);
        return mapper.toDTO(poll, userDetails);
    }

}