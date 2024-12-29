package com.genericcompany.user.config;

import com.genericcompany.user.service.EmailNotificationService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@Configuration
@ConditionalOnProperty(name = "email.service.enabled", havingValue = "true", matchIfMissing = false)
@EnableFeignClients(basePackages = "com.genericcompany.user.client")
public class EmailConfig {

    @Bean
    public EmailNotificationService emailNotificationService() {
        return new EmailNotificationService();
    }
}