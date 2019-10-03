package com.kodilla.ecommercee.config;

import com.kodilla.ecommercee.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
@AllArgsConstructor
public class EcommerceeConfig {

    private final JavaMailSender javaMailSender;

    @Bean
    public EmailService emailService() {
        return new EmailService(javaMailSender);
    }

}
