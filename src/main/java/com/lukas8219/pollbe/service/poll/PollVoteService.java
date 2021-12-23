package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.PollVoteDTO;

public interface PollVoteService {

    PollVoteDTO vote(Long id, boolean approved, PollUserDetails userDetails);

}
