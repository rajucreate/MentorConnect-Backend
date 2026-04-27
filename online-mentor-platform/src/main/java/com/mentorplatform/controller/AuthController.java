package com.mentorplatform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mentorplatform.auth.dto.AuthResponseDTO;
import com.mentorplatform.auth.dto.LoginRequestDTO;
import com.mentorplatform.dto.RegisterRequestDTO;
import com.mentorplatform.service.AuthService;

import jakarta.validation.Valid;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request) {

        String token = authService.login(request);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequestDTO request) {

        return ResponseEntity.status(201)
                .body(authService.register(request));
    }

    @GetMapping("/oauth2/authorize/google")
    public void authorizeGoogle(String role, HttpServletResponse response) throws Exception {
        String normalizedRole = role == null ? "MENTEE" : role.trim().toUpperCase();
        if (!normalizedRole.equals("MENTEE") && !normalizedRole.equals("MENTOR")) {
            normalizedRole = "MENTEE";
        }

        Cookie roleCookie = new Cookie("oauth_role", normalizedRole);
        roleCookie.setHttpOnly(true);
        roleCookie.setPath("/");
        roleCookie.setMaxAge(300);
        response.addCookie(roleCookie);

        String redirectUrl = UriComponentsBuilder
                .fromPath("/oauth2/authorization/google")
                .build()
                .toUriString();
        response.sendRedirect(redirectUrl);
    }
}