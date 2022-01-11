package com.lukas8219.pollbe.factory;

import com.lukas8219.pollbe.data.dto.NotificationType;
import com.lukas8219.pollbe.service.strategy.SendEmailStrategy;
import lombok.RequiredArgsConstructor;

import java.util.Map;


@RequiredArgsConstructor
public class SendEmailStrategyFactory {

    private final Map<NotificationType, SendEmailStrategy> sendEmailStrategyMap;

    public SendEmailStrategy getStrategy(NotificationType type) {
        return sendEmailStrategyMap.get(type);
    }

}
