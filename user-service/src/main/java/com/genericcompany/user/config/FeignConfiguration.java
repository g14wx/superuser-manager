package com.genericcompany.user.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.genericcompany.user.client")
public class FeignConfiguration {
}