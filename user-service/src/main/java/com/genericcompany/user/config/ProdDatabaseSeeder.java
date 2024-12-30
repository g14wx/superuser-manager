package com.genericcompany.user.config;

import com.genericcompany.user.model.Role;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class ProdDatabaseSeeder extends DatabaseSeeder {
    private final SeedConfiguration seedConfiguration;

    public ProdDatabaseSeeder(SeedConfiguration seedConfiguration) {
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
                seedConfiguration.getAdminName(),
                Role.REGULAR_USER
        );
    }
}

