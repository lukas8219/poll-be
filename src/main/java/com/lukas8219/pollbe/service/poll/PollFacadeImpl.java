package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.CreatePollDTO;
import com.lukas8219.pollbe.data.dto.PollDTO;
import com.lukas8219.pollbe.data.dto.PollVoteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollFacadeImpl implements PollFacade {

    private final PollCreateService createService;
    private final PollVoteService voteService;
    private final PollGetService getService;

    public PollDTO create(CreatePollDTO dto, PollUserDetails userDetails) {
        return createService.create(dto, userDetails);
    }

    public PollVoteDTO vote(Long id, boolean vote, PollUserDetails userDetails) {
        return voteService.vote(id, vote, userDetails);
    }

    public PollDTO getPoll(Long id, PollUserDetails userDetails) {
        return getService.get(id, userDetails);
    }

    public List<PollDTO> getAll(PollUserDetails userDetails) {
        return getService.getAll(userDetails);
    }
}