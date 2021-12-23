package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.UserDTO;
import com.lukas8219.pollbe.data.dto.UserEditDTO;
import com.lukas8219.pollbe.data.dto.UserPhotoDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserFacade {

    UserDTO edit(PollUserDetails userDetails, UserEditDTO dto);

    UserPhotoDTO editPhoto(PollUserDetails userDetails, MultipartFile file);

    UserDTO getUser(PollUserDetails userDetails);

}
