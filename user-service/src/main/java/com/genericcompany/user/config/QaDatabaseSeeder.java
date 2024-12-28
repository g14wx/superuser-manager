package com.genericcompany.user.config;

import com.genericcompany.user.model.Role;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("qa")
public class QaDatabaseSeeder extends DatabaseSeeder {

    private final SeedConfiguration seedConfiguration;

    public QaDatabaseSeeder(SeedConfiguration seedConfiguration) {
        super();
        this.seedConfiguration = seedConfiguration;
    }
    @Override
    protected void createInitialUsers() {
        super.createInitialUsers();
        // Add QA-specific users
        createUserIfNotFound(
                seedConfiguration.getQaUserEmail(),
                seedConfiguration.getQaUserPassword(),
                Role.REGULAR_USER
        );
        createUserIfNotFound(
                seedConfiguration.getTesterEmail(),
                seedConfiguration.getTesterPassword(),
                Role.REGULAR_USER
        );
    }
}
