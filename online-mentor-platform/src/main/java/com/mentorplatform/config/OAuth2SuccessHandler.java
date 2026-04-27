package com.mentorplatform.config;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.mentorplatform.model.User;
import com.mentorplatform.model.enums.Role;
import com.mentorplatform.repository.UserRepository;
import com.mentorplatform.service.EmailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;

    @Value("${app.frontend-url:http://localhost:5173}")
    private String frontendUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();

        String email = principal.getAttribute("email");
        if (email == null || email.isBlank()) {
            response.sendRedirect(frontendUrl + "/login?oauthError="
                    + encode("Google account did not provide an email."));
            return;
        }

        String rawName = principal.getAttribute("name");
        String resolvedName = (rawName == null || rawName.isBlank()) ? email : rawName;

        User user = userRepository.findByEmail(email).orElseGet(() -> createUser(email, resolvedName, request));

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        response.sendRedirect(frontendUrl + "/login?token=" + encode(token));
    }

    private User createUser(String email, String name, HttpServletRequest request) {
        Role role = resolveRoleFromCookie(request);

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
        User savedUser = userRepository.save(user);

        try {
            emailService.sendEmail(
                    savedUser.getEmail(),
                    "Welcome to MentorConnect 🎉",
                    "Hi " + savedUser.getName() + ",\n\n" +
                    "Your Google account has been successfully connected to MentorConnect.\n" +
                    "Your selected role: " + savedUser.getRole() + "\n\n" +
                    "You can now start using your account.\n\n" +
                    "— MentorConnect Team"
            );
        } catch (Exception ex) {
            System.out.println("Google welcome email failed: " + ex.getMessage());
        }

        return savedUser;
    }

    private Role resolveRoleFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Role.MENTEE;
        }

        for (Cookie cookie : request.getCookies()) {
            if ("oauth_role".equals(cookie.getName())) {
                String value = cookie.getValue();
                if ("MENTOR".equalsIgnoreCase(value)) {
                    return Role.MENTOR;
                }
                return Role.MENTEE;
            }
        }
        return Role.MENTEE;
    }

    private String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
