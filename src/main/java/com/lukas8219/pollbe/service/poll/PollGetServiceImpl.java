package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.PollDetailDTO;
import com.lukas8219.pollbe.exception.PollNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollGetServiceImpl implements PollGetService {

    private final PollGateway pollGateway;

    @Override
    public Poll get(Long id, PollUserDetails userDetails) {
        return pollGateway.findById(id)
                .orElseThrow(PollNotFoundException::new);
    }

    @Override
    public PollDetailDTO getDetails(Long id, PollUserDetails pollUserDetails) {
        return pollGateway.getDetails(id)
                .orElseThrow(PollNotFoundException::new);
    }

    @Override
    public List<Poll> getAll(PollUserDetails userDetails) {
        return pollGateway.findAll();
    }
}
