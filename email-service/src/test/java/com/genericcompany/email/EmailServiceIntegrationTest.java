package com.genericcompany.email;

import com.genericcompany.email.dto.EmailRequest;
import com.genericcompany.email.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceIntegrationTest {

    @Autowired
    private EmailService emailService;

    @MockBean
    private JavaMailSender mailSender;

    @Test
    public void testSendEmail() {
        EmailRequest request = new EmailRequest();
        request.setTo("test@test.com");
        request.setSubject("Test Subject");
        Map<String, Object> templateData = new HashMap<>();

        // Add some example data
        templateData.put("Name", "Ramiro Cuellar");
        request.setTemplateData(templateData);

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        doNothing().when(mailSender).send(any(MimeMessage.class));

        emailService.sendEmail(request);

        verify(mailSender).send(any(MimeMessage.class));
    }
}