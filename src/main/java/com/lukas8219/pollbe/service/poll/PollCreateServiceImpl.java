package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.CreatePollDTO;
import com.lukas8219.pollbe.exception.UserNotFoundException;
import com.lukas8219.pollbe.repository.PollRepository;
import com.lukas8219.pollbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PollCreateServiceImpl implements PollCreateService {

    private final PollRepository repository;
    private final UserRepository userRepository;

    public Poll create(CreatePollDTO dto, PollUserDetails userDetails) {
        var poll = new Poll();
        poll.setExpiresAt(dto.getExpiresAt());
        poll.setSubject(dto.getSubject());
        poll.setCreatedBy(userRepository.findById(userDetails.getId()).orElseThrow(UserNotFoundException::new));
        poll.setDescription(dto.getDescription());
        return repository.save(poll);
    }

}
