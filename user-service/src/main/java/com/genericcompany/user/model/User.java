package com.genericcompany.user.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Audited
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean active;

    private String passwordResetToken;

    private LocalDateTime passwordResetExpiry;

    private LocalDateTime lastLogin;

    // Getters, setters, and constructors
}