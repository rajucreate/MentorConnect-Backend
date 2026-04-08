package com.mentorplatform.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mentorplatform.dto.MentorshipMatchDTO;
import com.mentorplatform.dto.SessionResponseDTO;
import com.mentorplatform.model.MentorshipMatch;
import com.mentorplatform.model.Session;
import com.mentorplatform.model.User;
import com.mentorplatform.model.enums.MatchStatus;
import com.mentorplatform.model.enums.SessionStatus;
import com.mentorplatform.repository.MentorshipMatchRepository;
import com.mentorplatform.repository.SessionRepository;
import com.mentorplatform.repository.UserRepository;
import com.mentorplatform.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final MentorshipMatchRepository matchRepository;
    private final SessionRepository sessionRepository;

    // ✅ Users
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    // ✅ Matches
    @Override
    public List<MentorshipMatchDTO> getAllMatches() {
        return matchRepository.findAll().stream()
                .map(m -> new MentorshipMatchDTO(
                        m.getId(),
                        m.getMentor().getId(),
                        m.getMentor().getName(),
                        m.getMentor().getEmail(),
                        m.getMentee().getId(),
                        m.getMentee().getName(),
                        m.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void updateMatchStatus(Long matchId, MatchStatus status) {
        MentorshipMatch match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found"));

        match.setStatus(status);
        matchRepository.save(match);
    }

    // ✅ Sessions
    @Override
    public List<SessionResponseDTO> getAllSessions() {
        return sessionRepository.findAll().stream()
                .map(s -> new SessionResponseDTO(
                        s.getId(),
                        s.getMatch().getMentor().getName(),
                        s.getMatch().getMentee().getName(),
                        s.getStartTime(),
                        s.getEndTime(),
                        s.getMeetingLink(),
                        s.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void updateSessionStatus(Long sessionId, SessionStatus status) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        session.setStatus(status);
        sessionRepository.save(session);
    }
}