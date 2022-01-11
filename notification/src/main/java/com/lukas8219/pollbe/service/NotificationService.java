package com.lukas8219.pollbe.service;

import com.lukas8219.pollbe.data.dto.SendEmailDTO;

public interface NotificationService {

    void send(SendEmailDTO sendEmailDTO);

}
