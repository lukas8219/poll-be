package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.dto.PollVoteDTO;
import com.lukas8219.pollbe.data.mapper.PollVoteMapper;
import com.lukas8219.pollbe.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum.AGAINST;
import static com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum.FAVOR;

@Service
@RequiredArgsConstructor
public class PollVoteService {

    private final PollRepository repository;
    private final PollVoteMapper mapper;

    public PollVoteDTO vote(Long id, boolean approved) {
        var decision = approved ? FAVOR : AGAINST;
        var vote = new PollVote();
        vote.setDecision(decision);
        vote.setVotedAt(LocalDateTime.now());
        vote.setPoll(repository.findByIdAndExpiration(id).orElseThrow(RuntimeException::new));

        return mapper.toDTO(vote);
    }

}
