package com.lukas8219.pollbe.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FieldErrorDTO {

    private String field;
    private String message;
    private String rejectedValue;

}
