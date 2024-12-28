package com.genericcompany.user.config;

import com.genericcompany.user.model.Role;
import com.genericcompany.user.model.User;
import com.genericcompany.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component

public class DatabaseSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SeedConfiguration seedConfig;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        createInitialUsers();

        alreadySetup = true;
    }

    protected void createInitialUsers() {
        // Create admin and regular user for all environments
        createUserIfNotFound(
                seedConfig.getAdminEmail(),
                seedConfig.getAdminPassword(),
                Role.SUPER_ADMIN
        );

        createUserIfNotFound(
                seedConfig.getRegularUserEmail(),
                seedConfig.getRegularUserPassword(),
                Role.REGULAR_USER
        );
    }

    protected User createUserIfNotFound(String email, String password, Role role) {
        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            user = Optional.of(new User());
            user.get().setEmail(email);
            user.get().setPassword(passwordEncoder.encode(password));
            user.get().setRole(role);
            user.get().setActive(true);
            user = Optional.ofNullable(userRepository.save(user.get()));

            System.out.println("Created user: " + email + " with role: " + role);
        }

        return user.orElse(null);
    }
}
