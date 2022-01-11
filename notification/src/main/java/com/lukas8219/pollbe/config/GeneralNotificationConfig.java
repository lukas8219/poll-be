package com.lukas8219.pollbe.config;

import com.lukas8219.pollbe.factory.SendEmailStrategyFactory;
import com.lukas8219.pollbe.service.strategy.SendEmailStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class GeneralNotificationConfig {

    @Bean
    public SendEmailStrategyFactory init(@Autowired Map<String, SendEmailStrategy> strategyMap) {
        return new SendEmailStrategyFactory(strategyMap.values().stream()
                .collect(Collectors.toMap(SendEmailStrategy::getName, Function.identity())));
    }
}
