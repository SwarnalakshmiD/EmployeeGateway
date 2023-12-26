package com.quinbay.EmployeeAttendaceSystem.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration
public class WebConfig {

    @Bean
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }
}
