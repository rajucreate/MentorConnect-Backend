package com.mentorplatform.service;

import java.util.List;

import com.mentorplatform.dto.MentorshipMatchDTO;
import com.mentorplatform.dto.SessionResponseDTO;
import com.mentorplatform.model.User;
import com.mentorplatform.model.enums.MatchStatus;
import com.mentorplatform.model.enums.SessionStatus;

public interface AdminService {

    List<User> getAllUsers();

    void deleteUser(Long userId);

    List<MentorshipMatchDTO> getAllMatches();

    void updateMatchStatus(Long matchId, MatchStatus status);

    List<SessionResponseDTO> getAllSessions();

    void updateSessionStatus(Long sessionId, SessionStatus status);
}