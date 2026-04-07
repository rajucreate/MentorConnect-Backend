package com.mentorplatform.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mentorplatform.auth.dto.LoginRequestDTO;
import com.mentorplatform.config.JwtUtil;
import com.mentorplatform.dto.RegisterRequestDTO;
import com.mentorplatform.exception.InvalidOperationException;
import com.mentorplatform.exception.ResourceNotFoundException;
import com.mentorplatform.model.User;
import com.mentorplatform.repository.UserRepository;
import com.mentorplatform.service.AuthService;
import com.mentorplatform.service.EmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;

    // ✅ LOGIN
    @Override
    public String login(LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidOperationException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }

    // ✅ REGISTER
    @Override
    public String register(RegisterRequestDTO request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new InvalidOperationException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);

        // 📧 Send email (safe handling)
        try {
            emailService.sendEmail(
                    user.getEmail(),
                    "Welcome to MentorConnect 🎉",
                    "Hi " + user.getName() + ",\n\n" +
                    "Your account has been successfully created.\n" +
                    "Start exploring mentors now!\n\n" +
                    "— MentorConnect Team"
            );
        } catch (Exception e) {
            // ⚠️ Do NOT fail registration if email fails
            System.out.println("Email sending failed: " + e.getMessage());
        }

        return "User registered successfully";
    }
}