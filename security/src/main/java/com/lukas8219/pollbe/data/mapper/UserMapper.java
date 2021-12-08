package com.lukas8219.pollbe.data.mapper;

import com.lukas8219.pollbe.data.decorator.UserDecorator;
import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.data.domain.UserPhoto;
import com.lukas8219.pollbe.data.dto.UserDTO;
import com.lukas8219.pollbe.data.dto.UserPhotoDTO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(UserDecorator.class)
public interface UserMapper {

    UserDTO toDTO(User source);

    UserPhotoDTO toPhotoDTO(UserPhoto photo);

}
