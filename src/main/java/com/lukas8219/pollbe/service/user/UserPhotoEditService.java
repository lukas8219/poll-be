package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.UserPhoto;
import com.lukas8219.pollbe.data.interfaces.RequestFile;

import javax.transaction.Transactional;

public interface UserPhotoEditService {

    @Transactional
    UserPhoto edit(PollUserDetails userDetails, RequestFile file);

}
