package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.*;
import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import com.lukas8219.pollbe.data.mapper.PollMapperGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    @Override
    public PollParticipationDTO getLastCreation(PollUserDetails pollUserDetails) {
        return get(1);
    }

    @Override
    public List<PollParticipationDTO> getParticipations(PollUserDetails pollUserDetails) {
        return IntStream.range(0, 6).mapToObj(this::get).collect(Collectors.toList());
    }

    private PollParticipationDTO get(Integer integer){
        var poll = new PollParticipationDTO();
        var creator = new PollCreatorDetailsDTO("", "dev1@gmail.com", "Dev 1", Long.valueOf(integer));
        poll.setCreator(creator);
        poll.setAgainst(2);
        poll.setDecision(VoteDecisionEnum.FAVOR);
        poll.setFavor(3);
        poll.setSubject("Tal assunto");
        poll.setVotedAt(LocalDateTime.now().minusDays(3));
        poll.setResult(PollResultEnum.TIE);
        return poll;
    }
}
