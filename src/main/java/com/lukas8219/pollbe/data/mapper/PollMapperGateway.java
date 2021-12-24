package com.lukas8219.pollbe.data.mapper;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.dto.PollDTO;
import com.lukas8219.pollbe.data.dto.PollVoteDTO;

import java.util.List;

public interface PollMapperGateway {

    PollDTO toDTO(Poll poll, PollUserDetails userDetails);

    PollVoteDTO toDTO(PollVote vote);

    List<PollDTO> toDTO(List<Poll> dto, PollUserDetails userDetails);
}
