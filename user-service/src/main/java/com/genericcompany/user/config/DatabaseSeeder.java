package com.genericcompany.user.config;

import com.genericcompany.user.model.Role;
import com.genericcompany.user.model.User;
import com.genericcompany.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.DependsOn;

import java.util.Optional;

@Component
@DependsOn("passwordEncoder")
public class DatabaseSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SeedConfiguration seedConfig;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup || passwordEncoder == null) {
            return;
        }

        try {
            // Test password encoder
            String testEncode = passwordEncoder.encode("test");
            System.out.println("Password encoder test in seeder: " + testEncode);

            createInitialUsers();
            alreadySetup = true;
        } catch (Exception e) {
            System.err.println("Error in database seeder: " + e.getMessage());
            e.printStackTrace();
        }
    }

    protected void createInitialUsers() {
        if (passwordEncoder == null) {
            throw new IllegalStateException("PasswordEncoder not initialized");
        }

        createUserIfNotFound(
                seedConfig.getAdminEmail(),
                seedConfig.getAdminPassword(),
                seedConfig.getRegularName(),
                Role.SUPER_ADMIN
        );

        createUserIfNotFound(
                seedConfig.getRegularUserEmail(),
                seedConfig.getRegularUserPassword(),
                seedConfig.getRegularName(),
                Role.REGULAR_USER
        );
    }

    protected void createUserIfNotFound(String email, String password,String name, Role role) {
        Optional<User> userRes = userRepository.findByEmail(email);

        if (!userRes.isPresent()) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);
            user.setActive(true);
            user = userRepository.save(user);

            System.out.println("Created user: " + email + " with role: " + role);
        }
    }
}