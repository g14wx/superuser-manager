package com.genericcompany.user.service;

import com.genericcompany.user.client.EmailClient;
import com.genericcompany.user.client.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailNotificationService {

    @Autowired(required = false)
    private EmailClient emailClient;

    public void sendPasswordResetEmail(String email, String token) {
        if (emailClient != null) {
            EmailRequest request = new EmailRequest();
            request.setTo(email);
            request.setSubject("Superuser - Password Reset");
            Map<String, Object> templateData = new HashMap<>();
            String resetLink = "http://localhost:8080/reset-password?token=" + token;
            request.setTemplate("password-reset");
            templateData.put("resetLink", resetLink);
            request.setTemplateData(templateData);

            try {
                emailClient.sendEmail(request);
            } catch (Exception e) {
                System.err.println("Failed to send email: " + e.getMessage());
            }
        }
    }
}