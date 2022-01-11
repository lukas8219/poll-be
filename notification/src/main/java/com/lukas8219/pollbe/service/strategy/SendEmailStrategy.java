package com.lukas8219.pollbe.service;

import com.lukas8219.pollbe.data.dto.EmailDTO;
import com.lukas8219.pollbe.data.dto.NotificationType;

public interface SendEmailStrategy {

    NotificationType getName();

    EmailDTO convertMessage(String body);

}
