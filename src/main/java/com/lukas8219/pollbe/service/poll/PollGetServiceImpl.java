package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.exception.PollExpiredOrNotFoundException;
import com.lukas8219.pollbe.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PollGetServiceImpl implements PollGetService {

    private final PollRepository repository;

    public Poll get(Long id, PollUserDetails userDetails) {
        return repository.findByIdAndNotExpired(id)
                .orElseThrow(PollExpiredOrNotFoundException::new);
    }

    public List<Poll> getAll(PollUserDetails userDetails) {
        return new ArrayList<>(repository.findAll());
    }
}
