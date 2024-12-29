package com.genericcompany.user.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email-service")
public interface EmailClient {
    @PostMapping("/api/email/send")
    void sendEmail(@RequestBody EmailRequest request);
}