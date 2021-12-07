package com.lukas8219.pollbe.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        var allHttpMethods = Arrays.stream(HttpMethod.values())
                .map(HttpMethod::name)
                .collect(Collectors.toList())
                .toArray(String[]::new);

        registry
                .addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods(allHttpMethods);
    }
}
