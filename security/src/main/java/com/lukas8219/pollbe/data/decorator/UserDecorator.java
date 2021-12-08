package com.lukas8219.pollbe.data.decorator;

import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.data.domain.UserPhoto;
import com.lukas8219.pollbe.data.dto.UserDTO;
import com.lukas8219.pollbe.data.dto.UserPhotoDTO;
import com.lukas8219.pollbe.data.mapper.UserMapper;
import com.lukas8219.pollbe.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class UserDecorator implements UserMapper {

    @Autowired
    private UserMapper delegate;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public UserDTO toDTO(User source) {
        var result = delegate.toDTO(source);
        result.setPic(toPhotoDTO(source.getPhoto()));
        return result;
    }

    @Override
    public UserPhotoDTO toPhotoDTO(UserPhoto photo) {
        var result = new UserPhotoDTO();
        result.setLink(fileStorageService.getLink(photo));
        return result;
    }
}
