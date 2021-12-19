package com.lukas8219.pollbe.data.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String name;
    private String email;
    private String phoneNumber;
    private Long id;

    @JsonUnwrapped
    private UserPhotoDTO pic;

}
