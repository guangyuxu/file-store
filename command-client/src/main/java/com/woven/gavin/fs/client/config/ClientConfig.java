package com.woven.gavin.fs.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

    private final RestTemplate restTemplate =  new RestTemplate();

    @Bean
    public RestTemplate restTemplate(){
        return restTemplate;
    }
}
