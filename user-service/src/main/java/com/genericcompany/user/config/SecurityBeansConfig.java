package com.genericcompany.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityBeansConfig {

    @Bean(name = "passwordEncoder")
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // Verify the bean is working
        System.out.println("Password encoder created: " + encoder.encode("test"));
        return encoder;
    }
}