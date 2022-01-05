package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.CreatePollDTO;
import com.lukas8219.pollbe.data.dto.PollDTO;
import com.lukas8219.pollbe.data.dto.PollDetailDTO;
import com.lukas8219.pollbe.data.dto.PollVoteDTO;

import java.util.List;

public interface PollFacade {

    PollDTO create(CreatePollDTO dto, PollUserDetails userDetails);

    PollVoteDTO vote(Long id, boolean vote, PollUserDetails userDetails);

    PollDTO getPoll(Long id, PollUserDetails userDetails);

    PollDetailDTO getByDetail(Long id, PollUserDetails pollUserDetails);

    List<PollDTO> getAll(PollUserDetails userDetails);

}