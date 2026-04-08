package com.mentorplatform.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.mentorplatform.dto.SessionRequestDTO;
import com.mentorplatform.dto.SessionResponseDTO;
import com.mentorplatform.model.enums.SessionStatus;
import com.mentorplatform.repository.UserRepository;
import com.mentorplatform.service.SessionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    private final UserRepository userRepository;

    // ✅ MENTEE BOOKS SESSION (NO meeting link)
    @PreAuthorize("hasRole('MENTEE')")
    @PostMapping("/book")
    public ResponseEntity<String> bookSession(
            @RequestBody SessionRequestDTO request,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                sessionService.bookSession(email, request)
        );
    }

    // ✅ MENTEE - VIEW OWN SESSIONS
    @GetMapping("/my")
    @PreAuthorize("hasRole('MENTEE')")
    public ResponseEntity<List<SessionResponseDTO>> getMySessions(Authentication auth) {

        return ResponseEntity.ok(
                sessionService.getMySessions(auth.getName())
        );
    }

    // ✅ MENTOR - VIEW SESSIONS
    @GetMapping("/mentor")
    @PreAuthorize("hasRole('MENTOR')")
    public ResponseEntity<List<SessionResponseDTO>> getMentorSessions(Authentication auth) {

        return ResponseEntity.ok(
                sessionService.getMentorSessions(auth.getName())
        );
    }

    // ✅ MENTOR - APPROVE / REJECT
    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('MENTOR','ADMIN')")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long id,
            @RequestParam SessionStatus status) {

        sessionService.updateSessionStatus(id, status);
        return ResponseEntity.ok("Session status updated");
    }

    // 🔥 NEW API — MENTOR ADDS MEETING LINK
    @PutMapping("/{id}/link")
    @PreAuthorize("hasRole('MENTOR')")
    public ResponseEntity<String> addMeetingLink(
            @PathVariable Long id,
            @RequestParam String meetingLink) {

        sessionService.addMeetingLink(id, meetingLink);
        return ResponseEntity.ok("Meeting link added");
    }
}