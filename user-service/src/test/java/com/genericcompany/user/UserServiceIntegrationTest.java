package com.genericcompany.user;

import com.genericcompany.user.model.User;
import com.genericcompany.user.model.Role;
import com.genericcompany.user.repository.UserRepository;
import com.genericcompany.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testCreateUser() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("password123");

        User savedUser = userService.createUser(user, Role.REGULAR_USER);

        assertNotNull(savedUser.getId());
        assertTrue(savedUser.isActive());
        assertEquals(Role.REGULAR_USER, savedUser.getRole());
    }

    @Test
    @Transactional
    public void testToggleUserActive() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("password123");
        User savedUser = userService.createUser(user, Role.REGULAR_USER);

        userService.toggleUserActive(savedUser.getId());
        Optional<User> updatedUser = Optional.of(userService.getUserById(savedUser.getId()).get());

        assertFalse(updatedUser.get().isActive());
    }
}

