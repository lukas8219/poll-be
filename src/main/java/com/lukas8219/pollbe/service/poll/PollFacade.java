package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.dto.CreatePollDTO;
import com.lukas8219.pollbe.data.dto.PollDTO;
import com.lukas8219.pollbe.data.dto.PollVoteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PollFacade {

    private final PollCreateService createService;
    private final PollVoteService voteService;
    private final PollGetService getService;

    public PollDTO create(CreatePollDTO dto) {
        return createService.create(dto);
    }

    public PollVoteDTO vote(Long id, boolean vote) {
        return voteService.vote(id, vote);
    }

    public PollDTO getPoll(Long id) {
        return getService.get(id);
    }
}
