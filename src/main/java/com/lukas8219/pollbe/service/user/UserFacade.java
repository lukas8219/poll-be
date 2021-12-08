package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.UserDTO;
import com.lukas8219.pollbe.data.dto.UserEditDTO;
import com.lukas8219.pollbe.data.dto.UserPhotoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserEditService editService;
    private final UserPhotoEditService photoEditService;
    private final UserGetService getService;

    public UserDTO edit(PollUserDetails userDetails, UserEditDTO dto) {
        return editService.edit(userDetails, dto);
    }

    public UserPhotoDTO editPhoto(PollUserDetails userDetails, MultipartFile file) {
        return photoEditService.edit(userDetails, file);
    }

    public UserDTO getUser(PollUserDetails userDetails) {
        return getService.getUser(userDetails);
    }
}
