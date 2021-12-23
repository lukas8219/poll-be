package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.PollDTO;

import java.util.List;

public interface PollGetService {

    PollDTO get(Long id, PollUserDetails userDetails);

    List<PollDTO> getAll(PollUserDetails userDetails);

}
