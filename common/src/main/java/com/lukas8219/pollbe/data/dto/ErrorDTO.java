package com.lukas8219.pollbe.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorDTO {

    private Integer status;
    private String error;
    private Object fieldsError;
    private LocalDateTime timestamp;

}
