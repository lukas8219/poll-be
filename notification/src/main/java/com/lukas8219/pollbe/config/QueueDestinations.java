package com.lukas8219.pollbe.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "notification")
@Getter
@Setter
public class QueueDestinations {

    private String email;

}
