package com.lukas8219.pollbe.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPhotoDTO {

    @JsonProperty("pic")
    private String link;

}
