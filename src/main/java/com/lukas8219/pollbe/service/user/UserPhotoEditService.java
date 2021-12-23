package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.UserPhotoDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

public interface UserPhotoEditService {

    @Transactional
    UserPhotoDTO edit(PollUserDetails userDetails, MultipartFile file);

}
