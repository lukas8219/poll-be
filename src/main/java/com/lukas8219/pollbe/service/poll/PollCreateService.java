package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.CreatePollDTO;

public interface PollCreateService {

    Poll create(CreatePollDTO dto, PollUserDetails userDetails);
}
