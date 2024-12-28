package com.genericcompany.user.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.genericcompany.user.model.Role;

@Component
@Profile("dev")
public class DevDatabaseSeeder extends DatabaseSeeder {
    private final SeedConfiguration seedConfiguration;

    public DevDatabaseSeeder(SeedConfiguration seedConfiguration) {
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

