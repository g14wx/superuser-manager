package com.genericcompany.user;

import com.genericcompany.user.controller.AuthController;
import com.genericcompany.user.dto.AuthRequest;
import com.genericcompany.user.model.User;
import com.genericcompany.user.model.Role;
import com.genericcompany.user.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthControllerIntegrationTest {

    @Autowired
    private AuthController authController;

    @Autowired
    private UserService userService;

    private User testUser;

    @Before
    @Transactional
    public void setup() {
        testUser = new User();
        testUser.setEmail("test@test.com");
        testUser.setPassword("password123");
        userService.createUser(testUser, Role.REGULAR_USER);
    }

    @Test
    @Transactional
    public void testLoginSuccess() {
        AuthRequest request = new AuthRequest();
        request.setEmail("test@test.com");
        request.setPassword("password123");

        ResponseEntity<?> response = authController.login(request);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    @Transactional
    public void testPasswordReset() {
        ResponseEntity<?> response = authController.requestPasswordReset("test@test.com");
        assertEquals(200, response.getStatusCodeValue());
    }
}