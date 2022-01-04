package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.PollDetailDTO;
import com.lukas8219.pollbe.exception.PollExpiredOrNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollGetServiceImpl implements PollGetService {

    private final PollGateway pollGateway;

    public Poll get(Long id, PollUserDetails userDetails) {
        return pollGateway.findByIdAndNotExpired(id)
                .orElseThrow(PollExpiredOrNotFoundException::new);
    }

    @Override
    public PollDetailDTO getDetails(Long id, PollUserDetails pollUserDetails) {
        return pollGateway.getDetails(id)
                .orElseThrow(PollExpiredOrNotFoundException::new);
    }

    public List<Poll> getAll(PollUserDetails userDetails) {
        return pollGateway.findAll();
    }
}
