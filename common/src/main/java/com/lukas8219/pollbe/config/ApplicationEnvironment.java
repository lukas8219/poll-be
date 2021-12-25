package com.lukas8219.pollbe.config;

import com.lukas8219.pollbe.data.enumeration.EnvironmentEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class ApplicationEnvironment {

    private EnvironmentEnum environment;

}
