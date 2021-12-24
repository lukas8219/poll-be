package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.data.dto.UserDTO;
import com.lukas8219.pollbe.data.dto.UserEditDTO;

public interface UserEditService {

    User edit(PollUserDetails userDetails, UserEditDTO dto);

}
