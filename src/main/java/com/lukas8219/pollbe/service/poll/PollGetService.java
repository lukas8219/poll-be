package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.PollDetailDTO;

import java.util.List;

public interface PollGetService {

    Poll get(Long id, PollUserDetails userDetails);

    PollDetailDTO getDetails(Long id, PollUserDetails pollUserDetails);

    List<Poll> getAll(PollUserDetails userDetails);

}
