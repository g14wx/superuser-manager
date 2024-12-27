package com.genericcompany.user.controller;

import com.genericcompany.user.dto.AuthRequest;
import com.genericcompany.user.dto.AuthResponse;
import com.genericcompany.user.model.User;
import com.genericcompany.user.security.JwtTokenUtil;
import com.genericcompany.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userService.getUserByEmail(request.getEmail()).get();
        userService.updateLastLogin(user.getEmail());

        final String token = jwtTokenUtil.generateToken((UserDetails) user);

        return ResponseEntity.ok(new AuthResponse(token, user.getEmail(), user.getRole().toString()));
    }

    @PostMapping("/password-reset/request")
    public ResponseEntity<?> requestPasswordReset(@RequestParam String email) {
        String token = userService.initiatePasswordReset(email);
        // TODO: Send email with reset link
        return ResponseEntity.ok().build();
    }

    @PostMapping("/password-reset/reset")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        userService.resetPassword(token, newPassword);
        return ResponseEntity.ok().build();
    }
}