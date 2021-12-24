package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.UserDTO;
import com.lukas8219.pollbe.data.dto.UserEditDTO;
import com.lukas8219.pollbe.data.dto.UserPhotoDTO;
import com.lukas8219.pollbe.data.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserEditService editService;
    private final UserPhotoEditService photoEditService;
    private final UserGetService getService;

    private final UserMapper mapper;

    public UserDTO edit(PollUserDetails userDetails, UserEditDTO dto) {
        var result = editService.edit(userDetails, dto);
        return mapper.toDTO(result);
    }

    public UserPhotoDTO editPhoto(PollUserDetails userDetails, MultipartFile file) {
        var dto = photoEditService.edit(userDetails, file);
        return mapper.toPhotoDTO(dto);
    }

    public UserDTO getUser(PollUserDetails userDetails) {
        var dto = getService.getUser(userDetails.getId());
        return mapper.toDTO(dto);
    }
}
