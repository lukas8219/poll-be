package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;

import java.util.List;

public interface PollGetService {

    Poll get(Long id, PollUserDetails userDetails);

    List<Poll> getAll(PollUserDetails userDetails);

}
