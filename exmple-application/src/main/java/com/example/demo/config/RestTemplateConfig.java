package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import openschool.java.springbootstarter.logging.intercept.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(Optional<HttpLoggingInterceptor> httpLoggingInterceptor) {
        var restTemplate = new RestTemplate();
        httpLoggingInterceptor.ifPresent(interceptor ->
                restTemplate.setInterceptors(new ArrayList<>(Collections.singletonList(interceptor)))
        );
        return restTemplate;
    }
}
