package com.genericcompany.user.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class MigrationConfiguration {

    /**
     * Override default Flyway initializer to do nothing.
     */
    @Bean
    FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
        return new FlywayMigrationInitializer(flyway, (f) -> {
            // Prevent default initialization
        });
    }

    /**
     * Create a second Flyway initializer to run after JPA has created the schema.
     */
    @Bean
    @DependsOn("entityManagerFactory")
    FlywayMigrationInitializer delayedFlywayInitializer(Flyway flyway) {
        flyway.setBaselineOnMigrate(true); // Explicitly set baseline-on-migrate
        flyway.setBaselineVersionAsString("1"); // Set the baseline version
        return new FlywayMigrationInitializer(flyway);
    }
}
