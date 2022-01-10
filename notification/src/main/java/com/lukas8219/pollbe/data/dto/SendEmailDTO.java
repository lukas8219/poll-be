package com.lukas8219.pollbe.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailDTO {

    private NotificationType type;
    private String body;
}
