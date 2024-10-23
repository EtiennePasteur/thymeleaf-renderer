package com.etiennepasteur.thymeleaf_renderer;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MessageSourceConfig {

    @Bean
    public MessageSource messageSource() {
        try {
            return new JsonMessageSource();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}