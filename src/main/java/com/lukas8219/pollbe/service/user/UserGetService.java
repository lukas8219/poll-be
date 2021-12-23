package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.UserDTO;

public interface UserGetService {

    UserDTO getUser(PollUserDetails userDetails);

}
