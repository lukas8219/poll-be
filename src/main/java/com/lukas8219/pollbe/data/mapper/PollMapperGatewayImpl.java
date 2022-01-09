package com.lukas8219.pollbe.data.mapper;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.dto.PollDTO;
import com.lukas8219.pollbe.data.dto.PollListDTO;
import com.lukas8219.pollbe.data.dto.PollVoteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PollMapperGatewayImpl implements PollMapperGateway {

    private final PollMapper pollMapper;
    private final PollVoteMapper pollVoteMapper;

    @Override
    public PollDTO toDTO(Poll poll, PollUserDetails userDetails) {
        return pollMapper.toDTO(poll, userDetails);
    }

    @Override
    public PollVoteDTO toDTO(PollVote vote) {
        return pollVoteMapper.toDTO(vote);
    }

    @Override
    public List<PollListDTO> toDTO(List<Poll> dto, PollUserDetails userDetails) {
        return dto.stream()
                .map(x -> pollMapper.toListDTO(x, userDetails))
                .collect(Collectors.toList());
    }
}
