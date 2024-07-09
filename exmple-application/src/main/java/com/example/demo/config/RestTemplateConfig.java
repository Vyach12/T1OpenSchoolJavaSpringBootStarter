package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import openschool.java.springbootstarter.logging.intercept.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

    private final HttpLoggingInterceptor httpLoggingInterceptor;

    @Bean
    public RestTemplate appConfig() {
        var restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(httpLoggingInterceptor));
        return restTemplate;
    }
}
