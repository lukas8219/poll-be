package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.*;
import com.lukas8219.pollbe.data.mapper.PollMapperGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollFacadeImpl implements PollFacade {

    private final PollCreateService createService;
    private final PollVoteService voteService;
    private final PollGetService getService;
    private final PollMapperGateway mapperGateway;

    public PollDTO create(CreatePollDTO dto, PollUserDetails userDetails) {
        var poll = createService.create(dto, userDetails);
        return mapperGateway.toDTO(poll, userDetails);
    }

    public PollVoteDTO vote(Long id, boolean vote, PollUserDetails userDetails) {
        var dto = voteService.vote(id, vote, userDetails);
        return mapperGateway.toDTO(dto);
    }

    public PollDTO getPoll(Long id, PollUserDetails userDetails) {
        var dto = getService.get(id, userDetails);
        return mapperGateway.toDTO(dto, userDetails);
    }


    public List<PollListDTO> getAll(PollUserDetails userDetails) {
        var dto = getService.getAll(userDetails);
        return mapperGateway.toDTO(dto, userDetails);
    }
}
