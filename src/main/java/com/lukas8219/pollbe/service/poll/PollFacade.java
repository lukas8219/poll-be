package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.*;

import java.util.List;

public interface PollFacade {

    PollDTO create(CreatePollDTO dto, PollUserDetails userDetails);

    PollVoteDTO vote(Long id, boolean vote, PollUserDetails userDetails);

    PollDTO getPoll(Long id, PollUserDetails userDetails);

    List<PollListDTO> getAll(PollUserDetails userDetails);

    PollParticipationDTO getLastCreation(PollUserDetails pollUserDetails);

    List<PollParticipationDTO> getParticipations(PollUserDetails pollUserDetails);
}