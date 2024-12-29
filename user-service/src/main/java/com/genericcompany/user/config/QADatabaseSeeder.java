package com.genericcompany.user.config;

import com.genericcompany.user.model.Role;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class QADatabaseSeeder extends DatabaseSeeder {
    private final SeedConfiguration seedConfiguration;

    public QADatabaseSeeder(SeedConfiguration seedConfiguration) {
        super();
        this.seedConfiguration = seedConfiguration;
    }

    @Override
    protected void createInitialUsers() {
        super.createInitialUsers();
        // Add development-specific users
        createUserIfNotFound(
                seedConfiguration.getDevUserEmail(),
                seedConfiguration.getDevUserPassword(),
                Role.REGULAR_USER
        );
    }
}

