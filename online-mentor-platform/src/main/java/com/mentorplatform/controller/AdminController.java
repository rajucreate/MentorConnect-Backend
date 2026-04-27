package com.mentorplatform.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.mentorplatform.dto.MentorshipMatchDTO;
import com.mentorplatform.dto.SessionResponseDTO;
import com.mentorplatform.dto.UserResponseDTO;
import com.mentorplatform.model.enums.MatchStatus;
import com.mentorplatform.model.enums.SessionStatus;
import com.mentorplatform.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    // ✅ USERS
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    // ✅ MATCHES
    @GetMapping("/matches")
    public ResponseEntity<List<MentorshipMatchDTO>> getAllMatches() {
        return ResponseEntity.ok(adminService.getAllMatches());
    }

    @PutMapping("/match/{id}/status")
    public ResponseEntity<String> updateMatchStatus(
            @PathVariable Long id,
            @RequestParam MatchStatus status) {

        adminService.updateMatchStatus(id, status);
        return ResponseEntity.ok("Match status updated");
    }

    // ✅ SESSIONS
    @GetMapping("/sessions")
    public ResponseEntity<List<SessionResponseDTO>> getAllSessions() {
        return ResponseEntity.ok(adminService.getAllSessions());
    }

    @PutMapping("/session/{id}/status")
    public ResponseEntity<String> updateSessionStatus(
            @PathVariable Long id,
            @RequestParam SessionStatus status) {

        adminService.updateSessionStatus(id, status);
        return ResponseEntity.ok("Session status updated");
    }
}