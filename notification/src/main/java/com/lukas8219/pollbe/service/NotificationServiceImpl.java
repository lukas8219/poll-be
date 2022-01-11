package com.lukas8219.pollbe.service;

import com.lukas8219.pollbe.data.dto.NotificationType;
import com.lukas8219.pollbe.data.dto.SendEmailDTO;
import com.lukas8219.pollbe.factory.SendEmailStrategyFactory;
import com.lukas8219.pollbe.producer.NotificationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService{

    private final SendEmailStrategyFactory strategyFactory;
    private final NotificationProducer<SendEmailDTO> emailDTONotificationProducer;

    @Override
    public void send(SendEmailDTO sendEmailDTO) {
        var strategy = strategyFactory.getStrategy(sendEmailDTO.getType());
        log.info("Strategy is {}", strategy);
    }

    @Scheduled(fixedDelay = 500)
    public void produce(){
        emailDTONotificationProducer.publish(new SendEmailDTO(NotificationType.EMAIL, ""));
    }
}
