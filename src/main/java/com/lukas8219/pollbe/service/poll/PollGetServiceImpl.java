package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.exception.PollNotFoundException;
import com.lukas8219.pollbe.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollGetServiceImpl implements PollGetService {

    private final PollRepository repository;

    @Override
    public Poll get(Long id, PollUserDetails userDetails) {
        return repository.findById(id)
                .orElseThrow(PollNotFoundException::new);
    }

    @Override
    public List<Poll> getAll(PollUserDetails userDetails) {
        return repository.findAll();
    }
}
