package com.genericcompany.user.service;

import com.genericcompany.user.model.User;
import com.genericcompany.user.model.Role;
import com.genericcompany.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(User user, Role role) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        user.setActive(true);
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setRole(userDetails.getRole());
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        if (userDetails.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        return userRepository.save(user);
    }

    @Transactional
    public String initiatePasswordReset(String email) {
        Optional<User> userResponse = userRepository.findByEmail(email);
        if (!userResponse.isPresent()) {
            throw new RuntimeException("User not found");
        }
        User user = userResponse.get();
        String token = UUID.randomUUID().toString();
        user.setPasswordResetToken(token);
        user.setPasswordResetExpiry(LocalDateTime.now().plusHours(24));
        userRepository.save(user);

        return token;
    }

    @Transactional
    public void resetPassword(String token, String newPassword) {
        Optional<User> userResponse = userRepository.findByPasswordResetToken(token);
        if (!userResponse.isPresent()) {
            throw new RuntimeException("Invalid token");
        }
        User user = userResponse.get();
        if (user.getPasswordResetExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setPasswordResetToken(null);
        user.setPasswordResetExpiry(null);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void updateLastLogin(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            user.get().setLastLogin(LocalDateTime.now());
            userRepository.save(user.get());
        }
    }

    @Transactional
    public void toggleUserActive(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setActive(!user.isActive());
        userRepository.save(user);
    }

    @Transactional
    public void hardDelete(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        userRepository.delete(user);
    }
}