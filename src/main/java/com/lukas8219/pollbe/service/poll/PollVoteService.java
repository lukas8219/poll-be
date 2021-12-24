package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.PollVote;

public interface PollVoteService {

    PollVote vote(Long id, boolean approved, PollUserDetails userDetails);

}
