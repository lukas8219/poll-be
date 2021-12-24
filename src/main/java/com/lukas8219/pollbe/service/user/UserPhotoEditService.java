package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.UserPhoto;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

public interface UserPhotoEditService {

    @Transactional
    UserPhoto edit(PollUserDetails userDetails, MultipartFile file);

}
