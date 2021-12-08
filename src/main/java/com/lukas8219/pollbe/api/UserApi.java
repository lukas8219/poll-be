package com.lukas8219.pollbe.api;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.UserDTO;
import com.lukas8219.pollbe.data.dto.UserEditDTO;
import com.lukas8219.pollbe.data.dto.UserPhotoDTO;
import com.lukas8219.pollbe.service.user.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("v1/user/")
@RequiredArgsConstructor
public class UserApi {

    private final UserFacade facade;

    @PutMapping
    public UserDTO editUser(@AuthenticationPrincipal PollUserDetails userDetails,
                            @RequestBody UserEditDTO dto){
        return facade.edit(userDetails, dto);
    }

    @PostMapping("photo")
    public UserPhotoDTO editPhoto(@AuthenticationPrincipal PollUserDetails userDetails,
                                  @RequestPart MultipartFile file){
        return facade.editPhoto(userDetails, file);
    }
}
